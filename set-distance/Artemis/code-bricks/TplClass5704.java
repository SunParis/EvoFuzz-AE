import java.text.MessageFormat;

public class TplClass5704 {

    private static final void method(boolean err) throws Throwable {
        MessageFormat.format("Testdata {1,invalid_format_type}", new Object[] { "val0", "val1" });
        err = true;
    }
}

