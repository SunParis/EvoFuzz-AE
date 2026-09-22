public class TplClass4235 {

    private static final void method(int result, int[] x, int k, int l, int m, int n, int tc) throws Throwable {
        for (int i = 0; i < tc; i++) {
            // all used at once
            result += x[k] + x[l] + x[m] + x[n];
            int t = n;
            n = k;
            k = l;
            l = m;
            m = t;
        }
    }
}

