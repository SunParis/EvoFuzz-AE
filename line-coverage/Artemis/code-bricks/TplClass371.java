import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass371 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, int i, java.util.concurrent.atomic.AtomicIntegerArray b, int SCALE) throws Throwable {
        a.lazySet((i * SCALE), b.get(i * SCALE));
    }
}

