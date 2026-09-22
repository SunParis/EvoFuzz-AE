public class TplClass4138 {

    private static final void method(int[] x, int i) throws Throwable {
        int t = x[i - 3] ^ x[i - 8] ^ x[i - 14] ^ x[i - 16];
        x[i] = t << 1 | t >>> 31;
    }
}

