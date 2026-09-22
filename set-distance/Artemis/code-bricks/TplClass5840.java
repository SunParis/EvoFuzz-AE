public class TplClass5840 {

    private static final void method(java.lang.String val, int n) throws Throwable {
        try {
            n = Integer.parseInt(val);
        } catch (NumberFormatException nfe) {
            // Expected
            ;
        }
    }
}

