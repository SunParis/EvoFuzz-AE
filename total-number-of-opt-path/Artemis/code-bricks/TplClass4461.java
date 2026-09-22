public class TplClass4461 {

    private static final void method(int a, int i, int[] z) throws Throwable {
        a = z[i];
        for (int k = 0; k < 10; ++k) {
            a += z[k];
            a = z[i];
        }
    }
}

