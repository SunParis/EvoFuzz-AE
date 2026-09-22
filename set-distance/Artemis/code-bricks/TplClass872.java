public class TplClass872 {

    private static final void method(long[] val, long[] res, int failed, int ntests, java.lang.String[] test_name) throws Throwable {
        for (int i = 0; i < ntests; i++) {
            if (res[i] != val[i]) {
                failed++;
            }
        }
    }
}

