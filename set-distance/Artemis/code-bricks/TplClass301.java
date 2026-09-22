import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass301 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, int b) throws Throwable {
        for (int i = 0; i < ARRLEN; i += 1) {
            a.lazySet(i, b);
        }
    }
}

