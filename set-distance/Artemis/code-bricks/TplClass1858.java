public class TplClass1858 {

    private static final void method(int result, int indx, int sum, int min) throws Throwable {
        if (indx >= min) {
            sum += (sum ^ 9) + ((result != 0) ? 0 : sum);
            result = sum;
        }
    }
}

