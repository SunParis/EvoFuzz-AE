public class TplClass3797 {

    private static final void method(int[] a) throws Throwable {
        // relying on block ordering. (Test both `default+0` and `0+default`.)
        a[1] = 1;
        a[1] = 0;
    }
}

