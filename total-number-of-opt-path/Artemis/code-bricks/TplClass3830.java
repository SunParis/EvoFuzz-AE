public class TplClass3830 {

    private static final void method(boolean intr, java.lang.Integer[] mWaitOnMe, boolean otherDone) throws Throwable {
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
    }
}

