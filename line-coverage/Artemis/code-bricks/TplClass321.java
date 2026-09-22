import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass321 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, int b, int k) throws Throwable {
        for (int i = 0; i < ARRLEN - k; i += 1) {
            a.lazySet((i + k), b);
        }
    }
}

