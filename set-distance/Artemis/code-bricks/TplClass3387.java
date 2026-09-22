public class TplClass3387 {

    private static final void method(int i, boolean doThrow) throws Throwable {
        if (doThrow) {
        }
        // Set a value than does not fit in a 16-bit (signed) integer.
        i = 123456;
    }
}

