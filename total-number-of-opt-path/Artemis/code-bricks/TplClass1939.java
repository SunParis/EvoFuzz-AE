public class TplClass1939 {

    private static final void method(int NCOPY, int[] dst2, int OVERFLOW) throws Throwable {
        try {
            dst2 = new int[NCOPY + Integer.MAX_VALUE / 4 + OVERFLOW];
        } catch (OutOfMemoryError e) {
        }
    }
}

