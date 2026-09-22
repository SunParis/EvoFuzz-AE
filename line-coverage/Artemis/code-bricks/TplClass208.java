import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass208 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b) throws Throwable {
        for (int i = 0; i < ARRLEN; i += 1) {
            a.compareAndSet(i, -123, b.get(i));
        }
    }
}

