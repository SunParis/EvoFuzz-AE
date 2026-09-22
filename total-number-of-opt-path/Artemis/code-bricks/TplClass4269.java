public class TplClass4269 {

    private static final void method(int[] a) throws Throwable {
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                // Range [0,9]: safe.
                a[i * j] += 1;
            }
        }
    }
}

