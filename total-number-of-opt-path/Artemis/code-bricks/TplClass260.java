import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass260 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, int i, int OFFSET, int old) throws Throwable {
        a.compareAndSet((i + OFFSET), old, -123);
    }
}

