public class TplClass3836 {

    private static final void method(java.lang.Integer[] mWaitOnMe, boolean otherDone) throws Throwable {
        boolean intr = false;
        try {
            do {
                synchronized (mWaitOnMe) {
                    mWaitOnMe.wait(9000);
                }
            } while (!otherDone);
        } catch (InterruptedException ie) {
            intr = true;
        } catch (Exception ex) {
        }
        if (!intr)
            ;
    }
}

