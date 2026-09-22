public class TplClass4227 {

    private static final void method(long result, int[] ia, int index, long c, long d) throws Throwable {
        result = c * c;
        // array out of bound.
        ia[index] = 10;
        result += d;
    }
}

