public class TplClass2282 {

    private static final void method(float f1, float f2, float[] array, int n) throws Throwable {
        for (int i = 0; i < n; ++i) {
            array[i] = ((i & 1) == 1) ? f1 : f2;
            f1 += 1.5f;
            f2 += 2.25f;
        }
    }
}

