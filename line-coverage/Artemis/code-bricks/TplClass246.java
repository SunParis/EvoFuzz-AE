import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass246 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, int i, java.util.concurrent.atomic.AtomicIntegerArray b) throws Throwable {
        a.compareAndSet(i, -123, b.get(i));
    }
}

