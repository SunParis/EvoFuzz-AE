public class TplClass1962 {

    private static final void method(long[] durations) throws Throwable {
        // this empty for-loop is required to reproduce this bug
        for (long duration : durations) {
            // do nothing
        }
    }
}

