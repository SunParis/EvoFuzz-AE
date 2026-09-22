public class TplClass5601 {

    private static final void method(int errors, java.lang.String s) throws Throwable {
        try {
            long result = Long.parseUnsignedLong(s);
            // Should not reach here
            errors++;
        } catch (NumberFormatException nfe) {
            // Correct result
            ;
        }
    }
}

