public class TplClass4091 {

    private static final void method(int[] x, int a, int r) throws Throwable {
        for (int i = 0; i < 100; i++) {
            // a converges to 0
            r += x[a];
            a /= 5;
        }
    }
}

