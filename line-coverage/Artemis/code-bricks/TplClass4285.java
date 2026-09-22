public class TplClass4285 {

    private static final void method(int[] a, int r) throws Throwable {
        // Make sure short/int conversions compiles well (b/32193474).
        for (short i = 1; i < 10; i++) {
            int ki = i - 1;
            r += a[ki] + a[i];
        }
    }
}

