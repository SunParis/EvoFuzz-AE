public class TplClass4092 {

    private static final void method(int[] x, int a, int r) throws Throwable {
        for (int i = 0; i < 100; i++) {
            // a converges to 0
            r += x[a];
            // a wrap-around induction
            a %= 5;
        }
    }
}

