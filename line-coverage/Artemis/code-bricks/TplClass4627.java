public class TplClass4627 {

    private static final void method(java.lang.Thread[] readerThread) throws Throwable {
        for (int i = 0; i < readerThread.length; ++i) {
            readerThread[i].join();
        }
    }
}

