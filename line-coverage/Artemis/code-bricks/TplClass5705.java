public class TplClass5705 {

    private static final void method(boolean err, java.lang.IllegalArgumentException e) throws Throwable {
        String expected = "unknown format type: invalid_format_type";
        String got = e.getMessage();
        if (!expected.equals(got)) {
            err = true;
        }
    }
}

