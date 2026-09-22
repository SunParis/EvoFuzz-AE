import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass282 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int UNALIGN_OFF, int c, int d, int i) throws Throwable {
        a.getAndSet(i, c);
        b.getAndSet((i + UNALIGN_OFF), d);
    }
}

