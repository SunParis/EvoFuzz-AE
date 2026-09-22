import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass204 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, int i, java.util.concurrent.atomic.AtomicIntegerArray b, int UNALIGN_OFF) throws Throwable {
        a.set((i + UNALIGN_OFF), -123);
        b.set(i, -103);
    }
}

