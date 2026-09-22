import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass281 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, int i, java.util.concurrent.atomic.AtomicIntegerArray b, int UNALIGN_OFF) throws Throwable {
        a.compareAndSet((i + UNALIGN_OFF), -1, -123);
        b.getAndSet(i, -103);
    }
}

