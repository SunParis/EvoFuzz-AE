import java.util.concurrent.atomic.AtomicReferenceArray;

public class TplClass1983 {

    private static final void method(java.util.concurrent.atomic.AtomicReferenceArray a) throws Throwable {
        for (int i = 0; i < 100000; i++) {
            a.getAndSet(9999, new Object());
            if (i > 99990)
                System.gc();
        }
    }
}

