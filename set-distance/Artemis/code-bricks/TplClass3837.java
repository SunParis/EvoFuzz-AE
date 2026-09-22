public class TplClass3837 {

    private static final void method(java.lang.Integer[] mWaitOnMe, boolean otherDone) throws Throwable {
        synchronized (mWaitOnMe) {
            mWaitOnMe.wait(9000);
        }
    }
}

