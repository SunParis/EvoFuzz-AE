public class TplClass4507 {

    private static final void method(int[][] a, int sum) throws Throwable {
        // Make it hard to optimize out the arrays.
        for (int j = 0; j < 10; ++j) {
            sum += a[j][16] + /* = 0 */
            a[j][17];
        }
    }
}

