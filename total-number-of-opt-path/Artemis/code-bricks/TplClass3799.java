public class TplClass3799 {

    private static final void method(int[] a, int i) throws Throwable {
        // Cannot be eliminated due to aliasing.
        a[i] = 1;
    }
}

