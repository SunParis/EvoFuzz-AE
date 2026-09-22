public class TplClass5489 {

    private static final void method(java.lang.CharSequence aString, java.lang.String initString) throws Throwable {
        try {
            new StringBuilder(initString).insert(7, aString);
        } catch (IndexOutOfBoundsException soob) {
            // expected: passed
        } catch (Throwable t) {
        }
    }
}

