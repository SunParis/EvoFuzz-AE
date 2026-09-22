public class TplClass5098 {

    private static final void method() throws Throwable {
        /* these two are the real tests for this bug. */
        if ("foobar".toLowerCase() != "foobar")
            ;
        if ("FOOBAR".toUpperCase() != "FOOBAR")
            ;
        /* sanity test toLowerCase with some border conditions. */
        if (!("FooBar".toLowerCase().equals("foobar")))
            ;
        if (!("fooBar".toLowerCase().equals("foobar")))
            ;
        if (!("foobaR".toLowerCase().equals("foobar")))
            ;
        if (!("FOOBAR".toLowerCase().equals("foobar")))
            ;
        /* sanity test toUpperCase with some border conditions. */
        if (!("FooBar".toUpperCase().equals("FOOBAR")))
            ;
        if (!("fooBar".toUpperCase().equals("FOOBAR")))
            ;
        if (!("foobaR".toUpperCase().equals("FOOBAR")))
            ;
        if (!("foobar".toUpperCase().equals("FOOBAR")))
            ;
    }
}

