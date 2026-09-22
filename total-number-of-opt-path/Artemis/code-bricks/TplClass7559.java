import java.nio.charset.CharsetEncoder;
import java.io.PrintStream;

public class TplClass7559 {

    private static final void method(boolean can, java.nio.charset.CharsetEncoder ce, java.lang.String what, int errors, java.io.PrintStream out) throws Throwable {
        out.println(ce.charset().name() + ": Wrong answer for " + what + ": " + !can);
        errors++;
    }
}

