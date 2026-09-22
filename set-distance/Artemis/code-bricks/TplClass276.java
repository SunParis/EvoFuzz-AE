import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass276 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, int i, java.util.concurrent.atomic.AtomicIntegerArray b, int ALIGN_OFF) throws Throwable {
        a.getAndSet(i, b.get(i + ALIGN_OFF));
    }
}

