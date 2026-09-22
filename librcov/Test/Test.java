import java.util.Arrays;

public final class Test {
    // 尺寸尽量适中，能触发OSR/向量化又不至于太大
    static final int N = 1 << 16;

    // 防止DCE的简易黑洞
    static volatile double SINK_D;
    static volatile int SINK_I;
    static volatile Object SINK_O;

    public static void main(String[] args) {
        // 预热：触发解释执行、tiered 编译、OSR、收集类型profile
        for (int i = 0; i < 5; i++) {
            vectorMath();
            escapeAndLocks();
            devirtualization();
            arraysAndRCE();
        }

        long t0 = System.nanoTime();
        double a = vectorMath();
        long t1 = System.nanoTime();
        double b = escapeAndLocks();
        long t2 = System.nanoTime();
        int c = devirtualization();
        long t3 = System.nanoTime();
        int d = arraysAndRCE();
        long t4 = System.nanoTime();

        // 使用结果，避免被当作死代码
        SINK_D += a + b;
        SINK_I += c + d;

        System.out.println("vectorMath   = " + (t1 - t0) / 1_000_000.0 + " ms");
        System.out.println("escape+locks = " + (t2 - t1) / 1_000_000.0 + " ms");
        System.out.println("devirt       = " + (t3 - t2) / 1_000_000.0 + " ms");
        System.out.println("arrays+RCE   = " + (t4 - t3) / 1_000_000.0 + " ms");
        System.out.println("done: " + SINK_D + " / " + SINK_I);
    }

    // 触发点：
    // - SuperWord 向量化（float 数组线性访问）
    // - Range Check Elimination（单调索引）
    // - System.arraycopy/Math.sqrt 等 intrinsic
    // - 循环展开/OSR
    static double vectorMath() {
        int n = N;
        float[] a = new float[n];
        float[] b = new float[n];
        float[] c = new float[n];

        for (int i = 0; i < n; i++) {
            a[i] = i;
            b[i] = n - i;
        }

        double sum = 0.0;
        for (int k = 0; k < 5; k++) {     // 多轮以加热JIT
            for (int i = 0; i < n; i++) {
                // 线性、相同边界，利于RCE+向量化
                c[i] = a[i] * 1.1f + b[i];
                // 使用 Math.sqrt 以命中 intrinsic
                sum += Math.sqrt(Math.abs(c[i]));
            }
            // 命中 arraycopy intrinsic
            System.arraycopy(c, 0, a, 0, n);
        }
        // 黑洞，避免数组被消去
        SINK_O = c;
        return sum;
    }

    // 触发点：
    // - 逃逸分析：Value 对象标量替换/分配消除
    // - 锁消除：对非逃逸新对象的 synchronized
    // - 内联：小方法/构造器/Math.sqrt
    static double escapeAndLocks() {
        double acc = 0.0;
        for (int i = 0; i < 200_000; i++) {
            Object lock = new Object();     // 非逃逸 -> 锁消除
            synchronized (lock) {
                Value v = new Value(i, i + 1);  // 非逃逸 -> 分配消除/标量替换
                acc += v.len();
            }
        }
        return acc;
    }

    static final class Value {
        final double x, y;
        Value(double x, double y) { this.x = x; this.y = y; }
        double len() { return Math.sqrt(x * x + y * y); }  // intrinsic
    }

    // 触发点：
    // - 去虚拟化（类型单一 -> 单态调用）
    // - 内联（小接口实现方法）
    // - 常量折叠/强度削减
    static int devirtualization() {
        Op op = chooseOp(true);  // 返回单一具体类型，利于单态
        int s = 0;
        for (int i = 0; i < 2_000_000; i++) {
            s += op.apply(i);
        }
        // 轻触发另一类型，保持代码紧凑且不破坏单态热路径
        if ((s & 1) == 0) SINK_O = new Mul();
        return s;
    }

    interface Op { int apply(int x); }
    static final class Inc implements Op { public int apply(int x) { return x + 1; } }
    static final class Mul implements Op { public int apply(int x) { return x * 3; } }
    static Op chooseOp(boolean fast) { return fast ? new Inc() : new Mul(); }

    // 触发点：
    // - Arrays.fill intrinsic
    // - 两次相同边界的 for 循环，利于RCE/循环优化/内联边界检查折叠
    static int arraysAndRCE() {
        int[] a = new int[N];
        int[] b = new int[N];

        Arrays.fill(a, 1); // intrinsic
        for (int i = 0; i < N; i++) {
            b[i] = a[i] + i;
        }

        int s = 0;
        for (int i = 0; i < N; i++) {
            s += b[i];
        }
        return s;
    }
}


