import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass346 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, int i, java.util.concurrent.atomic.AtomicIntegerArray b) throws Throwable {
        a.lazySet(i, b.get(i));
    }
}

