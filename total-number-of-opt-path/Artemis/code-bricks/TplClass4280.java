public class TplClass4280 {

    private static final void method(int result, int[] x) throws Throwable {
        for (int i = 2; i <= 6; i++) {
            // Range analysis sees that innermost loop is finite and always taken.
            for (int j = i - 2; j <= i + 2; j++) {
                result += x[j];
            }
        }
    }
}

