public class TplClass2692 {

    private static final void method(java.lang.Object lock, int s, int state) throws Throwable {
        do {
            synchronized (lock) {
                s = state;
            }
        } while (// Busy loop.
        s != 1);
    }
}

