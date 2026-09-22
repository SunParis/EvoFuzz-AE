public class TplClass3351 {

    private static final void method(int res) throws Throwable {
        try {
            int[] x = new int[-1];
            res += 1;
        } catch (NegativeArraySizeException e) {
            res += 2;
        }
    }
}

