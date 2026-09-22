public class TplClass3881 {

    private static final void method(long elapsed, long finishTime, boolean success, long start, long startTime, long elapsedTime) throws Throwable {
        if (elapsed > 200) {
            success = false;
        } else {
            success = true;
            // println is occasionally very slow.
            finishTime = System.currentTimeMillis();
            startTime = start;
            elapsedTime = elapsed;
        }
    }
}

