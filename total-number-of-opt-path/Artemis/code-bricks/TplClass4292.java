public class TplClass4292 {

    private static final void method(int[] a) throws Throwable {
        try {
            for (int i = 0; i <= 3; i++) {
                for (int j = 0; j <= 3; j++) {
                    // Range [0,9]: safe.
                    a[i * j] += 1;
                }
            }
        } catch (Exception e) {
            a[0] += 1000;
        }
    }
}

