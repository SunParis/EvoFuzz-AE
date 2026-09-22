public class TplClass4233 {

    private static final void method(int result, int[] x, int k, int tc) throws Throwable {
        for (int i = 0; i < tc; i++) {
            result += x[k];
            k = 1 - k;
        }
    }
}

