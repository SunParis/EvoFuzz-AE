public class TplClass3779 {

    private static final void method(boolean x, int[] a) throws Throwable {
        if (x) {
            // Make sure the store below is not eliminated immediately as
            a[0] = 1;
            // storing the same value already present in the heap location.
            // Store the same value as default value to test merging with
            a[0] = 0;
            // the default value from else-block.
        } else {
            // Do the same as then-block for a different heap location to avoid
            // relying on block ordering. (Test both `default+0` and `0+default`.)
            a[1] = 1;
            a[1] = 0;
        }
    }
}

