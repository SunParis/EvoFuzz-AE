public class TplClass2015 {

    private static final void method(float[] a0) throws Throwable {
        int i = 0;
        for (; i < a0.length - 4; i += 4) {
            a0[i + 0] = 0.f;
            a0[i + 1] = 1.f;
            a0[i + 2] = 2.f;
            a0[i + 3] = 3.f;
        }
        for (; i < a0.length; i++) {
            a0[i] = (float) (i & 3);
        }
    }
}

