public class TplClass3805 {

    private static final void method(int zero, int i, int[] b) throws Throwable {
        b[i] = i;
        // Extra instructions to avoid loop unrolling.
        zero = (((zero ^ 1) + 2) ^ 1) - 2;
        zero = (((zero ^ 4) + 8) ^ 4) - 8;
    }
}

