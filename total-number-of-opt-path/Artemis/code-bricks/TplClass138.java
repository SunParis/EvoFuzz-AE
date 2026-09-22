import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass138 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, int OFFSET) throws Throwable {
        for (int i = 0; i < ARRLEN - OFFSET; i += 1) {
            a.set((i + OFFSET), -123);
        }
    }
}

