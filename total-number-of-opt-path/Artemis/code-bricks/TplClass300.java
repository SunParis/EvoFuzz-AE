import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass300 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a) throws Throwable {
        for (int i = 0; i < ARRLEN; i += 1) {
            a.lazySet(i, -123);
        }
    }
}

