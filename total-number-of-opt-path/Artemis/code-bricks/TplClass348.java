import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass348 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, int i, java.util.concurrent.atomic.AtomicIntegerArray b, int c, int d) throws Throwable {
        a.lazySet(i, c);
        b.lazySet(i, d);
    }
}

