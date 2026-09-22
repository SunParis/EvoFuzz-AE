public class TplClass2771 {

    private static final void method(int result, int[] x, int n) throws Throwable {
        for (int i = 0; i < n; i++) {
            int ii = i << 1;
            result += x[ii];
            result += x[ii + 1];
        }
    }
}

