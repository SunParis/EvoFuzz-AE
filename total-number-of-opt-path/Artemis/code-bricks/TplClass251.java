import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass251 {

    private static final void method(int limit, java.util.concurrent.atomic.AtomicIntegerArray a, int i, int old) throws Throwable {
        a.compareAndSet((limit - i), old, -123);
    }
}

