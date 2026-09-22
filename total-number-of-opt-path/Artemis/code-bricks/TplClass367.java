import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass367 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, int i, java.util.concurrent.atomic.AtomicIntegerArray b, int k) throws Throwable {
        a.lazySet((i + k), -123);
        b.lazySet((i + k), -103);
    }
}

