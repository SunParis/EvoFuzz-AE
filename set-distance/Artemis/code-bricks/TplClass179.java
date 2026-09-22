import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass179 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b) throws Throwable {
        int limit = ARRLEN - 1;
        for (int i = 0; i < ARRLEN; i += 1) {
            a.set((limit - i), -123);
            b.set(i, -103);
        }
    }
}

