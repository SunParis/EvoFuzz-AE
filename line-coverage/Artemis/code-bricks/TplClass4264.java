public class TplClass4264 {

    private static final void method(int[] a) throws Throwable {
        // As above, but with wrap around caused by an explicit conversion.
        for (int i = 0; i < a.length; ) {
            a[i] = i;
            i = (byte) (i + 1);
        }
    }
}

