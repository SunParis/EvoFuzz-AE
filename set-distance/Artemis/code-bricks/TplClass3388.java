public class TplClass3388 {

    private static final void method(boolean doThrow, int s) throws Throwable {
        if (doThrow) {
        }
        // Set a value than does not fit in a 16-bit (signed) integer.
        s = 456789;
    }
}

