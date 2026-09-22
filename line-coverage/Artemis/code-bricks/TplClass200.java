import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass200 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, int i, java.util.concurrent.atomic.AtomicIntegerArray b, int ALIGN_OFF) throws Throwable {
        a.set((i + ALIGN_OFF), -123);
        b.set(i, -103);
    }
}

