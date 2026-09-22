public class TplClass1967 {

    private static final void method(long[] durations) throws Throwable {
        for (int i = 0; i < 100000; i++) {
            // this empty for-loop is required to reproduce this bug
            for (long duration : durations) {
                // do nothing
            }
            {
                String s = "test";
                int len = s.length();
                s = s + s;
                len = len + len;
                s = s + s;
                len = len + len;
                s = s + s;
                len = len + len;
                if (s.length() != len) {
                }
            }
        }
    }
}

