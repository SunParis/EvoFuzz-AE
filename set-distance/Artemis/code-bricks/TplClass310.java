import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass310 {

    private static final void method(int ARRLEN, int limit, java.util.concurrent.atomic.AtomicIntegerArray a) throws Throwable {
        for (int i = 0; i < ARRLEN; i += 1) {
            a.lazySet((limit - i), -123);
        }
    }
}

