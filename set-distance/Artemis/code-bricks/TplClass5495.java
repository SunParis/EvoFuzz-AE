public class TplClass5495 {

    private static final void method() throws Throwable {
        StringBuilder sb = new StringBuilder();
        // should be NPE if null passed
        try {
            sb.indexOf(null);
        } catch (NullPointerException npe) {
            // expected: passed
        } catch (Throwable t) {
        }
    }
}

