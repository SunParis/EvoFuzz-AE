public class TplClass3796 {

    private static final void method(int[] a) throws Throwable {
        // Make sure the store below is not eliminated immediately as
        a[0] = 1;
        // Store the same value as default value to test merging with
        a[0] = 0;
    }
}

