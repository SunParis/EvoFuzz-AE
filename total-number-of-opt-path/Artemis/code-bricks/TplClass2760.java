public class TplClass2760 {

    private static final void method() throws Throwable {
        /* not expected to work; just exercises the call */
        try {
            System.loadLibrary("nonexistent");
        } catch (UnsatisfiedLinkError ule) {
        }
    }
}

