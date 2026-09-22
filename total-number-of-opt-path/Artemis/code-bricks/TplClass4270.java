public class TplClass4270 {

    private static final void method(int[] a, int i) throws Throwable {
        for (int j = 0; j <= 3; j++) {
            // Range [0,9]: safe.
            a[i * j] += 1;
        }
    }
}

