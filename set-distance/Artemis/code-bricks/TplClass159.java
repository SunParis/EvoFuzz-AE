import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass159 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int UNALIGN_OFF) throws Throwable {
        for (int i = 0; i < ARRLEN - UNALIGN_OFF; i += 1) {
            a.set((i + UNALIGN_OFF), -123);
            b.set(i, -103);
        }
    }
}

