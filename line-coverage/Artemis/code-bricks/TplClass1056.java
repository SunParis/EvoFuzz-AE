import java.io.PrintStream;

public class TplClass1056 {

    private static final void method(int errorStatus, java.io.PrintStream out, int errorLevel) throws Throwable {
        out.println("Test: failure #" + errorLevel);
        errorStatus = 2;
    }
}

