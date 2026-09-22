import java.util.concurrent.atomic.AtomicReferenceArray;

public class TplClass1986 {

    private static final void method(java.util.concurrent.atomic.AtomicReferenceArray a, int i) throws Throwable {
        a.getAndSet(9999, new Object());
        if (i > 99990)
            System.gc();
    }
}

