import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass294 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a1, int ARRLEN, int ALIGN_OFF) throws Throwable {
        for (int i = ALIGN_OFF; i < ARRLEN; i++) {
            a1.lazySet(i, -1);
        }
    }
}

