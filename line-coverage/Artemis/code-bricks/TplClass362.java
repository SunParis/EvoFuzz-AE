import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass362 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, int i, int OFFSET, java.util.concurrent.atomic.AtomicIntegerArray b) throws Throwable {
        a.lazySet((i + OFFSET), -123);
        b.lazySet((i + OFFSET), -103);
    }
}

