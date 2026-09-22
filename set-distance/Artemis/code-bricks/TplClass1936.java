public class TplClass1936 {

    private static final void method(java.lang.Thread thread2, java.lang.Thread thread1) throws Throwable {
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
        }
    }
}

