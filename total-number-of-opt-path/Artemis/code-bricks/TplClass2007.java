public class TplClass2007 {

    private static final void method(float[] a1, int i, float[] a0) throws Throwable {
        for (; i < a0.length - 4; i += 4) {
            a0[i + 0] = a1[i + 0] + 0.f;
            a0[i + 1] = a1[i + 1] + 1.f;
            a0[i + 2] = a1[i + 2] + 2.f;
            a0[i + 3] = a1[i + 3] + 3.f;
        }
    }
}

