public class TplClass4234 {

    private static final void method(int result, int[] x, int k, int l, int tc) throws Throwable {
        for (int i = 0; i < tc; i++) {
            result += x[k];
            int t = l;
            l = k;
            k = t;
        }
    }
}

