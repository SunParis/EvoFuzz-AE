public class TplClass2995 {

    private static final void method(int[] a) throws Throwable {
        a[a.length - 3] = 1;
        a[a.length - 2] = 2;
        a[a.length - 1] = 3;
        // (2) exposed bug in removing same BC twice if (1) would not be done.
        a[a.length - 0] = 4;
    }
}

