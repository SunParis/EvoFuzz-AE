public class TplClass2004 {

    private static final void method(float[] a1, float[] a0) throws Throwable {
        for (int i = 0; i < a0.length; i += 1) {
            a0[i] = a1[i] / (float) ((i & 3) + 1);
        }
    }
}

