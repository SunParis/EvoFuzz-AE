import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass134 {

    private static final void method(int limit, java.util.concurrent.atomic.AtomicIntegerArray a, int b) throws Throwable {
        for (int i = limit; i >= 0; i -= 1) {
            a.set((limit - i), b);
        }
    }
}

