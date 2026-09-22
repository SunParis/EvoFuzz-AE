import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class TplClass1925 {

    private static final void method(java.lang.Object str, java.lang.ref.WeakReference<java.lang.Object> wr, java.lang.ref.ReferenceQueue<java.lang.Object> rq) throws Throwable {
        Thread reader = new Thread() {

            public void run() {
                while (wr.get() != null) {
                }
            }
        };
        Thread queueReader = new Thread() {

            public void run() {
                try {
                    Reference<? extends Object> ref = rq.remove();
                } catch (InterruptedException e) {
                }
            }
        };
        reader.start();
        queueReader.start();
        Thread.sleep(1000);
        str = null;
        System.gc();
    }
}

