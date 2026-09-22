public class TplClass1855 {

    private static final void method(int result, int indx, int[] b, int sum, int min) throws Throwable {
        for (indx = b.length - 1; indx >= 0; --indx) {
            if (indx >= min) {
                sum += (sum ^ 9) + ((result != 0) ? 0 : sum);
                result = sum;
            }
        }
    }
}

