public class TplClass5763 {

    private static final void method(java.lang.CharSequence aString, java.lang.String initString) throws Throwable {
        try {
            new StringBuffer(initString).insert(7, aString);
        } catch (IndexOutOfBoundsException soob) {
            // expected: passed
        } catch (Throwable t) {
        }
    }
}

