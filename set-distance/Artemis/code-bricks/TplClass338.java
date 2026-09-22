import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass338 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a1, java.util.concurrent.atomic.AtomicIntegerArray a2, int i) throws Throwable {
        a1.lazySet(i, -1);
        a2.lazySet(i, -1);
    }
}

