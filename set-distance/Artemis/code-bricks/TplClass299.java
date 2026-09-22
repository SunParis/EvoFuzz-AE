import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass299 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a1, int UNALIGN_OFF) throws Throwable {
        for (int i = 0; i < UNALIGN_OFF; i++) {
            a1.lazySet((i + UNALIGN_OFF), -1);
        }
    }
}

