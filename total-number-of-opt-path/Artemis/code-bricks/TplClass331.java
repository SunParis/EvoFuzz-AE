import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass331 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int ALIGN_OFF) throws Throwable {
        for (int i = 0; i < ARRLEN - ALIGN_OFF; i += 1) {
            a.lazySet(i, b.get(i + ALIGN_OFF));
        }
    }
}

