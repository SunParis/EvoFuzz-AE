public class TplClass3703 {

    private static final void method(boolean doThrow) throws Throwable {
        // Prevent inlining to avoid the string comparison being optimized away.
        if (doThrow) {
        }
    }
}

