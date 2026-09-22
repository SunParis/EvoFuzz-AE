import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass366 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, int i, java.util.concurrent.atomic.AtomicIntegerArray b, int k) throws Throwable {
        a.lazySet((i + k), b.get(i + k));
    }
}

