import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass280 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, int i, java.util.concurrent.atomic.AtomicIntegerArray b, int UNALIGN_OFF) throws Throwable {
        a.getAndSet(i, b.get(i + UNALIGN_OFF));
    }
}

