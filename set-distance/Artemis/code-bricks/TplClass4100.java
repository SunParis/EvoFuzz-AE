public class TplClass4100 {

    private static final void method(int[] x, int a, int r) throws Throwable {
        // a converges to 0
        r += x[a];
        // a wrap-around induction
        a %= 5;
    }
}

