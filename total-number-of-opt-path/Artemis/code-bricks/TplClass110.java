import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass110 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a1, int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a2) throws Throwable {
        // Reset for indexing with invariant offset
        for (int i = 0; i < ARRLEN; i++) {
            a1.set(i, -1);
            a2.set(i, -1);
        }
    }
}

