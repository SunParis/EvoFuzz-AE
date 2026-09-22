public class TplClass1854 {

    private static final void method(int result, int indx, int[] b, int sum) throws Throwable {
        for (indx = -7; indx < b.length; ++indx) {
            if (indx <= 9) {
                sum += (sum ^ 15) + ((result != 0) ? 0 : sum);
                result = sum;
            }
        }
    }
}

