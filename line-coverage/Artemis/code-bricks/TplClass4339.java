public class TplClass4339 {

    private static final void method(int a1, int[] x, int a2, int r) throws Throwable {
        for (int i = 0; i < 5; i++) {
            // two polynomials combined into new polynomial
            int t = a1 + a2;
            r -= x[t];
            a1 += (3 * i + 1);
            a2 += (2 * i);
        }
    }
}

