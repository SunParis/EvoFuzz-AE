import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass267 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, int i, java.util.concurrent.atomic.AtomicIntegerArray b, int k) throws Throwable {
        a.compareAndSet((i + k), -123, b.get(i + k));
    }
}

