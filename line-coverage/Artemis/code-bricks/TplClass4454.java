public class TplClass4454 {

    private static final void method(int a, int i, int[] z) throws Throwable {
        for (int j = i; j < 10; ++j) {
            a = z[i];
            for (int k = 0; k < 10; ++k) {
                a += z[k];
                a = z[i];
            }
        }
    }
}

