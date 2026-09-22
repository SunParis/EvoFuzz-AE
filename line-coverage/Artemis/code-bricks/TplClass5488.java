public class TplClass5488 {

    private static final void method(java.lang.StringBuffer sb) throws Throwable {
        // should be NPE if null passed
        try {
            sb.indexOf(null, 1);
        } catch (NullPointerException npe) {
            // expected: passed
        } catch (Throwable t) {
        }
    }
}

