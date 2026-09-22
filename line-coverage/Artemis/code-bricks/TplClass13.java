import java.util.concurrent.atomic.AtomicReferenceArray;

public class TplClass13 {

    private static final void method() throws Throwable {
        AtomicReferenceArray<Integer> x = new AtomicReferenceArray(128);
        Integer y = new Integer(0);
        for (int i = 0; i < 50000; i++) {
            x.getAndSet(i % x.length(), y);
        }
    }
}

