import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass356 {

    private static final void method(int limit, java.util.concurrent.atomic.AtomicIntegerArray a, int i, java.util.concurrent.atomic.AtomicIntegerArray b) throws Throwable {
        a.lazySet((limit - i), -123);
        b.lazySet(i, -103);
    }
}

