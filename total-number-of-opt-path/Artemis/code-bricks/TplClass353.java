import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass353 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b) throws Throwable {
        int limit = ARRLEN - 1;
        for (int i = 0; i < ARRLEN; i += 1) {
            a.lazySet(i, b.get(limit - i));
        }
    }
}

