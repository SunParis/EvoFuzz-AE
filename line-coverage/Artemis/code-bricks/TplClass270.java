import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass270 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, int i, int SCALE, int old) throws Throwable {
        a.compareAndSet((i * SCALE), old, -123);
    }
}

