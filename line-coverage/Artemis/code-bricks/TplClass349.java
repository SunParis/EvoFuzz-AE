import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass349 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a) throws Throwable {
        int limit = ARRLEN - 1;
        for (int i = 0; i < ARRLEN; i += 1) {
            a.lazySet((limit - i), -123);
        }
    }
}

