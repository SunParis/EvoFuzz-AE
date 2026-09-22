public class TplClass5853 {

    private static final void method(java.lang.String[] outOfRange, int errors) throws Throwable {
        for (String s : outOfRange) {
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
}

