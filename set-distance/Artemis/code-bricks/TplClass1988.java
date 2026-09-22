public class TplClass1988 {

    private static final void method(int iter, int i, int[] d) throws Throwable {
        // try to defeat dead code elimination
        if (d[0] == d[1]) {
        }
    }
}

