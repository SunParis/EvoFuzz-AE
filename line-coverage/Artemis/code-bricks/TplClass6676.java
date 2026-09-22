public class TplClass6676 {

    private static final void method(boolean done) throws Throwable {
        while (!done) {
            System.gc();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }
}

