public class TplClass4222 {

    private static final void method(int result, int[] ia, int c, int d) throws Throwable {
        try {
            result = c * c;
            // array out of bound.
            ia[c] = d;
            result += d;
        } catch (Exception e) {
        }
    }
}

