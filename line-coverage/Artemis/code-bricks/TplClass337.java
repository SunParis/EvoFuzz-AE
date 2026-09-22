import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass337 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int UNALIGN_OFF, int c, int d) throws Throwable {
        for (int i = 0; i < ARRLEN - UNALIGN_OFF; i += 1) {
            a.lazySet(i, c);
            b.lazySet((i + UNALIGN_OFF), d);
        }
    }
}

