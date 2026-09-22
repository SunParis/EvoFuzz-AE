public class TplClass4297 {

    private static final void method(int result, int[] x, int k, int l, int m, int n) throws Throwable {
        // all used at once
        result += x[k] + x[l] + x[m] + x[n];
        int t = n;
        n = k;
        k = l;
        l = m;
        m = t;
    }
}

