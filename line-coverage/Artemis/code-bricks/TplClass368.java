import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass368 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int c, int d, int i, int k) throws Throwable {
        a.lazySet((i + k), c);
        b.lazySet((i + k), d);
    }
}

