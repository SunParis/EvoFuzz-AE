public class TplClass4281 {

    private static final void method(int result, int[] x, int i) throws Throwable {
        // Range analysis sees that innermost loop is finite and always taken.
        for (int j = i - 2; j <= i + 2; j++) {
            result += x[j];
        }
    }
}

