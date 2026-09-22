import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass296 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a1, int ARRLEN) throws Throwable {
        for (int i = 0; i < ARRLEN; i++) {
            a1.lazySet(i, -1);
        }
    }
}

