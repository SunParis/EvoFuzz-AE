import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass374 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, int i, java.util.concurrent.atomic.AtomicIntegerArray b, int ALIGN_OFF) throws Throwable {
        a.lazySet((i + ALIGN_OFF), b.get(i));
    }
}

