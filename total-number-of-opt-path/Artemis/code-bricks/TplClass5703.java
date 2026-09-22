import java.text.MessageFormat;

public class TplClass5703 {

    private static final void method() throws Throwable {
        boolean err = false;
        try {
            MessageFormat.format("Testdata {1,invalid_format_type}", new Object[] { "val0", "val1" });
            err = true;
        } catch (IllegalArgumentException e) {
            String expected = "unknown format type: invalid_format_type";
            String got = e.getMessage();
            if (!expected.equals(got)) {
                err = true;
            }
        } catch (Exception e) {
            err = true;
        }
        if (err) {
        }
    }
}

