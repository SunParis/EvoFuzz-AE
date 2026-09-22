import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass325 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, int SCALE) throws Throwable {
        for (int i = 0; i * SCALE < ARRLEN; i += 1) {
            a.lazySet((i * SCALE), -123);
        }
    }
}

