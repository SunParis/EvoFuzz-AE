public class TplClass5875 {

    private static final void method(int errors, java.lang.String s) throws Throwable {
        try {
            int result = Integer.parseUnsignedInt(s);
            // Should not reach here
            errors++;
        } catch (NumberFormatException nfe) {
            // Correct result
            ;
        }
    }
}

