public class TplClass1857 {

    private static final void method(int result, int indx, int sum) throws Throwable {
        if (indx <= 9) {
            sum += (sum ^ 15) + ((result != 0) ? 0 : sum);
            result = sum;
        }
    }
}

