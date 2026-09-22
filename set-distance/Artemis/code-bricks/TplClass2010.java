public class TplClass2010 {

    private static final void method(float[] a1, int i, float[] a0) throws Throwable {
        for (; i < a0.length; i++) {
            a0[i] = a1[i] / (float) ((i & 3) + 1);
        }
    }
}

