import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass203 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, int i, java.util.concurrent.atomic.AtomicIntegerArray b, int UNALIGN_OFF) throws Throwable {
        a.set(i, b.get(i + UNALIGN_OFF));
    }
}

