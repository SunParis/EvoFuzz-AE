import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass173 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a) throws Throwable {
        int limit = ARRLEN - 1;
        for (int i = 0; i < ARRLEN; i += 1) {
            a.set((limit - i), -123);
        }
    }
}

