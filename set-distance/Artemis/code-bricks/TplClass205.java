import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass205 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int UNALIGN_OFF, int c, int d, int i) throws Throwable {
        a.set(i, c);
        b.set((i + UNALIGN_OFF), d);
    }
}

