public class TplClass5002 {

    private static final void method() throws Throwable {
        Exception e = new Exception();
        AssertionError ae = new AssertionError(e);
        if (ae.getCause() != e)
            ;
        ae = new AssertionError("gosh it's late");
        if (ae.getCause() != null)
            ;
    }
}

