public class TplClass1965 {

    private static final void method(int i, long[] durations) throws Throwable {
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

