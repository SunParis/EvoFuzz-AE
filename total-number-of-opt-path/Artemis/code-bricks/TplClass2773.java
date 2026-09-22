public class TplClass2773 {

    private static final void method(int result, int[] x) throws Throwable {
        // This case is not optimized.
        for (int i = 0; i < x.length; i += 2) {
            result += x[i];
        }
    }
}

