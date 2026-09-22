import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass316 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, int OFFSET, int b) throws Throwable {
        for (int i = 0; i < ARRLEN - OFFSET; i += 1) {
            a.lazySet((i + OFFSET), b);
        }
    }
}

