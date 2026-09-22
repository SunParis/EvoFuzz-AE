import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass182 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int c, int d, int limit, int i) throws Throwable {
        a.set(i, c);
        b.set((limit - i), d);
    }
}

