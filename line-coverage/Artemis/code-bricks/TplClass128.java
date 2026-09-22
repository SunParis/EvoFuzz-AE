import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass128 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a) throws Throwable {
        for (int i = ARRLEN - 1; i >= 0; i -= 1) {
            a.set(i, -123);
        }
    }
}

