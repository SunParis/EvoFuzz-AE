public class TplClass2803 {

    private static final void method(int[] a, int i) throws Throwable {
        // wrap-around to -128 when i exceeds 127.
        for (int j = (byte) i; j < 200; j++) {
            a[j] += 1;
        }
    }
}

