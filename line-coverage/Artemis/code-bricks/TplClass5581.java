public class TplClass5581 {

    private static final void method(java.lang.String[] outOfRange, int errors) throws Throwable {
        for (String s : outOfRange) {
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
}

