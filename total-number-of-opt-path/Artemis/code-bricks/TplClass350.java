import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass350 {

    private static final void method(int limit, java.util.concurrent.atomic.AtomicIntegerArray a, int i) throws Throwable {
        a.lazySet((limit - i), -123);
    }
}

