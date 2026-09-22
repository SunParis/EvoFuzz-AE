public class TplClass4223 {

    private static final void method(long result, int[] ia, int index, long c, long d) throws Throwable {
        try {
            result = c * c;
            // array out of bound.
            ia[index] = 10;
            result += d;
        } catch (Exception e) {
        }
    }
}

