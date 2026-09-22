public class TplClass5842 {

    private static final void method(java.lang.String val) throws Throwable {
        int n = 0;
        try {
            n = Integer.parseInt(val);
        } catch (NumberFormatException nfe) {
            // Expected
            ;
        }
    }
}

