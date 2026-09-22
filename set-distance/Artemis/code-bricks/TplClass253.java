import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass253 {

    private static final void method(int limit, java.util.concurrent.atomic.AtomicIntegerArray a, int i, int b, int old) throws Throwable {
        a.compareAndSet((limit - i), old, b);
    }
}

