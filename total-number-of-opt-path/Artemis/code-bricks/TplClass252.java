import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass252 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, int b, int old) throws Throwable {
        int limit = ARRLEN - 1;
        for (int i = limit; i >= 0; i -= 1) {
            a.compareAndSet((limit - i), old, b);
        }
    }
}

