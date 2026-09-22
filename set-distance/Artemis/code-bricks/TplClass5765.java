public class TplClass5765 {

    private static final void method() throws Throwable {
        StringBuffer sb = new StringBuffer();
        // should be NPE if null passed
        try {
            sb.indexOf(null);
        } catch (NullPointerException npe) {
            // expected: passed
        } catch (Throwable t) {
        }
    }
}

