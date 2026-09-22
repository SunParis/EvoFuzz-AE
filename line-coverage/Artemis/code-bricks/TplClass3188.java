public class TplClass3188 {

    private static final void method(java.lang.Thread[] sThreads) throws Throwable {
        // because one test relies on the contents of this array to be consistent.
        for (int i = 0; i < 10; i++) {
            sThreads[i].start();
        }
    }
}

