public class TplClass5493 {

    private static final void method() throws Throwable {
        StringBuffer sb = new StringBuffer();
        // should be NPE if null passed
        try {
            sb.indexOf(null, 1);
        } catch (NullPointerException npe) {
            // expected: passed
        } catch (Throwable t) {
        }
    }
}

