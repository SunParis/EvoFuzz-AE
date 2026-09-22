import java.lang.ref.WeakReference;

public class TplClass7596 {

    private static final void method(java.lang.Object[] chain, java.lang.ref.WeakReference<java.lang.Object> wr) throws Throwable {
        while (wr.get() != null) {
            try {
                Object[] allocate = new Object[1000000];
                allocate[0] = chain;
                chain = allocate;
            } catch (OutOfMemoryError oome) {
                chain = null;
            }
            System.gc();
            Thread.sleep(100);
        }
    }
}

