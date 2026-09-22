import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass201 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int c, int d, int ALIGN_OFF, int i) throws Throwable {
        a.set(i, c);
        b.set((i + ALIGN_OFF), d);
    }
}

