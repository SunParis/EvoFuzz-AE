import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass363 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int c, int d, int i, int OFFSET) throws Throwable {
        a.lazySet((i + OFFSET), c);
        b.lazySet((i + OFFSET), d);
    }
}

