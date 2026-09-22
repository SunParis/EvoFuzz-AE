public class TplClass1856 {

    private static final void method(int result, int indx, int sum, int max) throws Throwable {
        if (indx <= max) {
            sum += (indx ^ 15) + ((result != 0) ? 0 : sum);
            result = sum;
        }
    }
}

