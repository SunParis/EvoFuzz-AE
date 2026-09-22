public class TplClass998 {

    private static final void method(long runNumber) throws Throwable {
        // then run less hot.
        if (runNumber > 15000) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

