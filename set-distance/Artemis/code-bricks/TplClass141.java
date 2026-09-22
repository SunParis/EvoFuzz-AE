import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass141 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, int OFFSET, java.util.concurrent.atomic.AtomicIntegerArray b) throws Throwable {
        for (int i = 0; i < ARRLEN - OFFSET; i += 1) {
            a.set((i + OFFSET), -123);
            b.set((i + OFFSET), -103);
        }
    }
}

