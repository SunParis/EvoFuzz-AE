import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass354 {

    private static final void method(int limit, java.util.concurrent.atomic.AtomicIntegerArray a, int i, java.util.concurrent.atomic.AtomicIntegerArray b) throws Throwable {
        a.lazySet(i, b.get(limit - i));
    }
}

