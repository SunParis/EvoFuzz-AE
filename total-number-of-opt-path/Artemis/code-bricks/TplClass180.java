import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass180 {

    private static final void method(int limit, java.util.concurrent.atomic.AtomicIntegerArray a, int i, java.util.concurrent.atomic.AtomicIntegerArray b) throws Throwable {
        a.set((limit - i), -123);
        b.set(i, -103);
    }
}

