public class TplClass748 {

    private static final void method(double[] val, double[] res, int failed, int ntests, java.lang.String[] test_name) throws Throwable {
        for (int i = 0; i < ntests; i++) {
            if (res[i] != val[i]) {
                failed++;
            }
        }
    }
}

