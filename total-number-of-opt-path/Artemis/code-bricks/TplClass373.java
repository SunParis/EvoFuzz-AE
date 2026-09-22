import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass373 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int c, int d, int i, int SCALE) throws Throwable {
        a.lazySet((i * SCALE), c);
        b.lazySet((i * SCALE), d);
    }
}

