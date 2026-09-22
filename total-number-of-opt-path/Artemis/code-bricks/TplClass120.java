import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass120 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a1, int UNALIGN_OFF) throws Throwable {
        // Reset for unaligned overlap initialization
        for (int i = 0; i < UNALIGN_OFF; i++) {
            a1.set(i, i);
        }
    }
}

