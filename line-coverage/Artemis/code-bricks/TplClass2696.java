public class TplClass2696 {

    private static final void method(java.lang.Object lock, int state, int[] values) throws Throwable {
        int s;
        do {
            synchronized (lock) {
                s = state;
            }
        } while (// Busy loop.
        s != 1);
        synchronized (lock) {
            values = null;
            state = 2;
        }
    }
}

