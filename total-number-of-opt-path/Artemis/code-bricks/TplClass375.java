import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass375 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, int i, java.util.concurrent.atomic.AtomicIntegerArray b, int ALIGN_OFF) throws Throwable {
        a.lazySet(i, b.get(i + ALIGN_OFF));
    }
}

