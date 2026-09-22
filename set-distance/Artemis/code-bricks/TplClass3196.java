public class TplClass3196 {

    private static final void method(java.lang.Thread[] sThreads) throws Throwable {
        try {
            for (int i = 0; i < 10; i++) {
                sThreads[i].join();
            }
        } catch (InterruptedException e) {
        }
    }
}

