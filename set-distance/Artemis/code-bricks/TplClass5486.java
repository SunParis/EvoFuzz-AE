public class TplClass5486 {

    private static final void method(java.lang.StringBuilder sb) throws Throwable {
        // should be NPE if null passed
        try {
            sb.indexOf(null);
        } catch (NullPointerException npe) {
            // expected: passed
        } catch (Throwable t) {
        }
    }
}

