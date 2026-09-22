public class TplClass4 {

    private static final void method(int progressState, java.lang.Object progressLock, int state) throws Throwable {
        synchronized (progressLock) {
            progressState = state;
            progressLock.notify();
        }
    }
}

