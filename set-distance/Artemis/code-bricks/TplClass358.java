import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass358 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int c, int d, int limit, int i) throws Throwable {
        a.lazySet(i, c);
        b.lazySet((limit - i), d);
    }
}

