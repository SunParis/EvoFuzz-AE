public class TplClass4127 {

    private static final void method(int[] x) throws Throwable {
        // Two redundant deopts are removed by simplifier.
        for (int i = 16; i < 80; i++) {
            int t = x[i - 3] ^ x[i - 8] ^ x[i - 14] ^ x[i - 16];
            x[i] = t << 1 | t >>> 31;
        }
    }
}

