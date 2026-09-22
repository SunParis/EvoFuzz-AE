import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass136 {

    private static final void method(int ARRLEN, int limit, java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b) throws Throwable {
        for (int i = 0; i < ARRLEN; i += 1) {
            a.set((limit - i), -123);
            b.set(i, -103);
        }
    }
}

