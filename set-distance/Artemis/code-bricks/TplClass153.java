import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass153 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int ALIGN_OFF) throws Throwable {
        for (int i = 0; i < ARRLEN - ALIGN_OFF; i += 1) {
            a.set((i + ALIGN_OFF), b.get(i));
        }
    }
}

