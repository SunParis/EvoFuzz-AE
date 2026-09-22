public class TplClass2700 {

    private static final void method(java.lang.Object lock, int state, int[] values) throws Throwable {
        int[] vs1;
        synchronized (lock) {
            vs1 = values;
            state = 1;
        }
        int s;
        do {
            synchronized (lock) {
                s = state;
            }
        } while (// Busy loop.
        s != 2);
        int[] vs2 = values;
        int v1 = vs1[0];
        int v2 = vs2[0];
    }
}

