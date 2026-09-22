import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass293 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a1, int ALIGN_OFF) throws Throwable {
        // Reset for aligned overlap initialization
        for (int i = 0; i < ALIGN_OFF; i++) {
            a1.lazySet(i, i);
        }
    }
}

