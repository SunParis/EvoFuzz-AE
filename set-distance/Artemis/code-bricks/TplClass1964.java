public class TplClass1964 {

    private static final void method() throws Throwable {
        long[] durations = new long[60];
        for (int i = 0; i < 100000; i++) {
            // this empty for-loop is required to reproduce this bug
            for (long duration : durations) {
                // do nothing
            }
            {
                String s = "test";
                int len = s.length();
                s = new StringBuilder(String.valueOf(s)).append(s).toString();
                len = len + len;
                s = new StringBuilder(String.valueOf(s)).append(s).toString();
                len = len + len;
                s = new StringBuilder(String.valueOf(s)).append(s).toString();
                len = len + len;
                if (s.length() != len) {
                }
            }
        }
    }
}

