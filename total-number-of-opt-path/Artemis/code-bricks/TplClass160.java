import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass160 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int UNALIGN_OFF, int c, int d) throws Throwable {
        for (int i = 0; i < ARRLEN - UNALIGN_OFF; i += 1) {
            a.set(i, c);
            b.set((i + UNALIGN_OFF), d);
        }
    }
}

