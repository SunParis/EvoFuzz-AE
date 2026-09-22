import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class TplClass1926 {

    private static final void method(java.lang.ref.WeakReference<java.lang.Object> wr, java.lang.ref.ReferenceQueue<java.lang.Object> rq) throws Throwable {
        Reference<? extends Object> ref = rq.remove();
    }
}

