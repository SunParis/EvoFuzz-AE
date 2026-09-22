import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass145 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int k) throws Throwable {
        for (int i = 0; i < ARRLEN - k; i += 1) {
            a.set((i + k), b.get(i + k));
        }
    }
}

