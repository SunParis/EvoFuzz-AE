import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass185 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, int i, int OFFSET, java.util.concurrent.atomic.AtomicIntegerArray b) throws Throwable {
        a.set((i + OFFSET), b.get(i + OFFSET));
    }
}

