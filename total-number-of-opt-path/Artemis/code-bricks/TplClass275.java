import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass275 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, int i, java.util.concurrent.atomic.AtomicIntegerArray b, int ALIGN_OFF) throws Throwable {
        a.compareAndSet((i + ALIGN_OFF), -1, b.get(i));
    }
}

