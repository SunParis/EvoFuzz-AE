public class TplClass1853 {

    private static final void method(int result, int indx, int[] b, int sum, int max) throws Throwable {
        for (indx = 0; indx < b.length; ++indx) {
            if (indx <= max) {
                sum += (indx ^ 15) + ((result != 0) ? 0 : sum);
                result = sum;
            }
        }
    }
}

