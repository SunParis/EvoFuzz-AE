public class TplClass4460 {

    private static final void method(int acc, int[][] x, int i, int j, float f) throws Throwable {
        // before the dynamic BCE phase runs.
        f++;
        // cause the test to fail.
        acc += x[i][i];
    }
}

