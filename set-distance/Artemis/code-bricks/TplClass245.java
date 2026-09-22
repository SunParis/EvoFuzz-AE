import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass245 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, int i, int b, int old) throws Throwable {
        a.compareAndSet(i, old, b);
    }
}

