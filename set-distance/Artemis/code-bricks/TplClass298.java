import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass298 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a1, int ARRLEN, int UNALIGN_OFF) throws Throwable {
        for (int i = UNALIGN_OFF; i < ARRLEN; i++) {
            a1.lazySet(i, -1);
        }
    }
}

