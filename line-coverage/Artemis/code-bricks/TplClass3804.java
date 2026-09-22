public class TplClass3804 {

    private static final void method(int zero, int[] a, int i) throws Throwable {
        a[i] = i;
        // Extra instructions to avoid loop unrolling.
        zero = (((zero ^ 1) + 2) ^ 1) - 2;
        zero = (((zero ^ 4) + 8) ^ 4) - 8;
    }
}

