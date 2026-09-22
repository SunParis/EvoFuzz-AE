public class TplClass4493 {

    private static final void method(int ARRAY_SIZE, int[] a, byte[] b, char[] c, short[] s, double[] d, float[] f, long[] l) throws Throwable {
        for (int i = 0; i < ARRAY_SIZE; i++) {
            b[i] += 1;
        }
        for (int i = 0; i < ARRAY_SIZE; i++) {
            s[i] += -128;
        }
        for (int i = 0; i < ARRAY_SIZE; i++) {
            c[i] += 127;
        }
        for (int i = 0; i < ARRAY_SIZE; i++) {
            a[i] += -219;
        }
        for (int i = 0; i < ARRAY_SIZE; i++) {
            a[i] += 219;
        }
        for (int i = 0; i < ARRAY_SIZE; i++) {
            l[i] += 219;
        }
        for (int i = 0; i < ARRAY_SIZE; i++) {
            f[i] += 2.0f;
        }
        for (int i = 0; i < ARRAY_SIZE; i++) {
            f[i] += 14.34f;
        }
        for (int i = 0; i < ARRAY_SIZE; i++) {
            d[i] += 20.0;
        }
        for (int i = 0; i < ARRAY_SIZE; i++) {
            d[i] += 0.0;
        }
    }
}

