public class TplClass4453 {

    private static final void method(int a, int[] z) throws Throwable {
        for (int i = 0; i < 10; ++i) {
            for (int j = i; j < 10; ++j) {
                a = z[i];
                for (int k = 0; k < 10; ++k) {
                    a += z[k];
                    a = z[i];
                }
            }
        }
    }
}

