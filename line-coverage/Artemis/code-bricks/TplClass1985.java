import java.util.concurrent.atomic.AtomicReferenceArray;

public class TplClass1985 {

    private static final void method() throws Throwable {
        AtomicReferenceArray a = new AtomicReferenceArray(10000);
        for (int i = 0; i < 100000; i++) {
            a.getAndSet(9999, new Object());
            if (i > 99990)
                System.gc();
        }
    }
}

