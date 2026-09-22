public class TplClass2283 {

    private static final void method(double[] array, double d1, double d2, int n) throws Throwable {
        for (int i = 0; i < n; ++i) {
            array[i] = ((i & 1) == 1) ? d1 : d2;
            d1 += 1.5;
            d2 += 2.25;
        }
    }
}

