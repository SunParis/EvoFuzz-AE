import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass372 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, int i, java.util.concurrent.atomic.AtomicIntegerArray b, int SCALE) throws Throwable {
        a.lazySet((i * SCALE), -123);
        b.lazySet((i * SCALE), -103);
    }
}

