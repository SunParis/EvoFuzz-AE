public class TplClass7598 {

    private static final void method(java.lang.Object[] chain) throws Throwable {
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

