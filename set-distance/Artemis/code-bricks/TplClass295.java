import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass295 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a1, int ALIGN_OFF) throws Throwable {
        for (int i = 0; i < ALIGN_OFF; i++) {
            a1.lazySet((i + ALIGN_OFF), -1);
        }
    }
}

