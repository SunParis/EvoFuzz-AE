import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass239 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int c, int d, int ALIGN_OFF) throws Throwable {
        for (int i = 0; i < ARRLEN - ALIGN_OFF; i += 1) {
            a.getAndSet(i, c);
            b.getAndSet((i + ALIGN_OFF), d);
        }
    }
}

