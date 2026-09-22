import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass150 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int SCALE) throws Throwable {
        for (int i = 0; i * SCALE < ARRLEN; i += 1) {
            a.set((i * SCALE), b.get(i * SCALE));
        }
    }
}

