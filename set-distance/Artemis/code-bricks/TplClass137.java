import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass137 {

    private static final void method(int limit, java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int c, int d) throws Throwable {
        for (int i = limit; i >= 0; i -= 1) {
            a.set(i, c);
            b.set((limit - i), d);
        }
    }
}

