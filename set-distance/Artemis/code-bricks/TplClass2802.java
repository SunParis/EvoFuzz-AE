public class TplClass2802 {

    private static final void method(int[] a) throws Throwable {
        for (int i = 0; i < 200; i++) {
            // Lower bound must be recognized as lower precision induction with arithmetic
            // wrap-around to -128 when i exceeds 127.
            for (int j = (byte) i; j < 200; j++) {
                a[j] += 1;
            }
        }
    }
}

