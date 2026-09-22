import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass336 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int UNALIGN_OFF) throws Throwable {
        for (int i = 0; i < ARRLEN - UNALIGN_OFF; i += 1) {
            a.lazySet((i + UNALIGN_OFF), -123);
            b.lazySet(i, -103);
        }
    }
}

