public class TplClass2739 {

    private static final void method(int[] tooBig) throws Throwable {
        try {
            final int COUNT = 32768 * 32768 + 4;
            tooBig = new int[COUNT];
        } catch (OutOfMemoryError oom) {
        }
    }
}

