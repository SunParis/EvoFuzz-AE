public class TplClass2765 {

    private static final void method(int result, int[] x) throws Throwable {
        for (int i = 0; i < x.length; i++) {
            int k = (-i) + (i << 5) + i - (32 * i) + 5 + (int) i;
            result += x[k - 5];
        }
    }
}

