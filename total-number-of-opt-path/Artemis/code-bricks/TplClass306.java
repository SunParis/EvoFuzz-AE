import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass306 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, int b) throws Throwable {
        for (int i = ARRLEN - 1; i >= 0; i -= 1) {
            a.lazySet(i, b);
        }
    }
}

