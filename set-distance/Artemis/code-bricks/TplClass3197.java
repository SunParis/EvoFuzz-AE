public class TplClass3197 {

    private static final void method(java.lang.Runnable r, java.lang.Thread[] sThreads) throws Throwable {
        for (int i = 0; i < 10; i++) {
            sThreads[i] = new Thread(r);
        }
        // because one test relies on the contents of this array to be consistent.
        for (int i = 0; i < 10; i++) {
            sThreads[i].start();
        }
    }
}

