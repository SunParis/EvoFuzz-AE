public class TplClass2503 {

    private static final void method(boolean value, boolean doThrow) throws Throwable {
        if (value) {
            // We're not inlining throw at the moment.
            if (doThrow) {
            }
        }
    }
}

