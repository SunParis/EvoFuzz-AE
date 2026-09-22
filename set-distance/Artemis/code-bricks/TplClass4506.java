public class TplClass4506 {

    private static final void method(int[][] a) throws Throwable {
        for (int j = 0; j < 10; ++j) {
            a[j] = new int[50000 * j + 20];
            a[j][17] = 1;
        }
    }
}

