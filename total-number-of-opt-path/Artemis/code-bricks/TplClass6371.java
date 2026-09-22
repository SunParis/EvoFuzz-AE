public class TplClass6371 {

    private static final void method(java.lang.Thread thr) throws Throwable {
        // terminate
        do {
            try {
                thr.join();
            } catch (InterruptedException x) {
            }
        } while (thr.isAlive());
    }
}

