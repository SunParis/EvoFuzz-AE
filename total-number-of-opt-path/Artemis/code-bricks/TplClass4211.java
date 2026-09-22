public class TplClass4211 {

    private static final void method(int[] a, int i, int j, int k) throws Throwable {
        j = i - 1;
        // and second should not be revisited after forward BCE.
        k = a[i] + a[i - 1];
    }
}

