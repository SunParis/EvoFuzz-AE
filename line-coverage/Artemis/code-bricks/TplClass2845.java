public class TplClass2845 {

    private static final void method(char c, java.lang.String foo) throws Throwable {
        for (int i = 0; i < 10; i++) {
            // The charAt may be licm'ed, but it has to be licm'ed with its
            // bounds check.
            c = foo.charAt(10000000);
        }
    }
}

