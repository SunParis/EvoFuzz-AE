public class TplClass1969 {

    private static final void method(int i, long[] durations) throws Throwable {
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

