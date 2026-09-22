import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass266 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, int i, int b, int k, int old) throws Throwable {
        a.compareAndSet((i + k), old, b);
    }
}

