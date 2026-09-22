public class TplClass5762 {

    private static final void method(java.lang.StringBuffer sb) throws Throwable {
        // should be NPE if null passed
        try {
            sb.indexOf(null);
        } catch (NullPointerException npe) {
            // expected: passed
        } catch (Throwable t) {
        }
    }
}

