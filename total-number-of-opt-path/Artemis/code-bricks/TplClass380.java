import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass380 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, int i, java.util.concurrent.atomic.AtomicIntegerArray b, int UNALIGN_OFF) throws Throwable {
        a.lazySet((i + UNALIGN_OFF), -123);
        b.lazySet(i, -103);
    }
}

