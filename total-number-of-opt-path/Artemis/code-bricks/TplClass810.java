public class TplClass810 {

    private static final void method(float[] val, float[] res, int failed, int ntests, java.lang.String[] test_name) throws Throwable {
        for (int i = 0; i < ntests; i++) {
            if (res[i] != val[i]) {
                failed++;
            }
        }
    }
}

