public class TplClass3827 {

    private static final void method(java.lang.Integer[] mWaitOnMe, boolean otherDone) throws Throwable {
        do {
            synchronized (mWaitOnMe) {
                mWaitOnMe.wait(9000);
            }
        } while (!otherDone);
    }
}

