public class TplClass5 {

    private static final void method(int progressState, java.lang.Object progressLock, int state) throws Throwable {
        progressState = state;
        progressLock.notify();
    }
}

