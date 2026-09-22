public class TplClass3725 {

    private static final void method(boolean doThrow) throws Throwable {
        // Try defeating inlining.
        if (doThrow) {
        }
    }
}

