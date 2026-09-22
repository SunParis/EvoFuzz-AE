import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass288 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a1, int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a2) throws Throwable {
        // Reset for indexing with scale
        for (int i = 0; i < ARRLEN; i++) {
            a1.lazySet(i, -1);
            a2.lazySet(i, -1);
        }
    }
}

