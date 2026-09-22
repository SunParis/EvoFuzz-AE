public class TplClass5574 {

    private static final void method(java.lang.String val, long n) throws Throwable {
        try {
            n = Long.parseLong(val);
        } catch (NumberFormatException nfe) {
            // Expected
            ;
        }
    }
}

