public class TplClass3187 {

    private static final void method(java.lang.Runnable r, java.lang.Thread[] sThreads) throws Throwable {
        for (int i = 0; i < 10; i++) {
            sThreads[i] = new Thread(r);
        }
    }
}

