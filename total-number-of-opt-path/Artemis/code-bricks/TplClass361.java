import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass361 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, int i, int OFFSET, java.util.concurrent.atomic.AtomicIntegerArray b) throws Throwable {
        a.lazySet((i + OFFSET), b.get(i + OFFSET));
    }
}

