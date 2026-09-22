import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass249 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, int i, int old) throws Throwable {
        a.compareAndSet(i, old, -123);
    }
}

