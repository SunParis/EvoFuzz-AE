public class TplClass4225 {

    private static final void method(int result, int[] ia, int c, int d) throws Throwable {
        result = c * c;
        // array out of bound.
        ia[c] = d;
        result += d;
    }
}

