import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass377 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int c, int d, int ALIGN_OFF, int i) throws Throwable {
        a.lazySet(i, c);
        b.lazySet((i + ALIGN_OFF), d);
    }
}

