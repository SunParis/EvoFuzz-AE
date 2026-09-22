public class TplClass2846 {

    private static final void method(java.lang.String myString) throws Throwable {
        try {
            String foo = myString;
            // Make sure the null check is not in the loop.
            foo.getClass();
            char c = 0;
            for (int i = 0; i < 10; i++) {
                // The charAt may be licm'ed, but it has to be licm'ed with its
                // bounds check.
                c = foo.charAt(10000000);
            }
        } catch (StringIndexOutOfBoundsException e) {
            // Expected
        }
    }
}

