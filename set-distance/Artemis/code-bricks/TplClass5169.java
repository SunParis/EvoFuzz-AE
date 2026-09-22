import java.io.PrintWriter;
import java.util.List;

public class TplClass5169 {

    private static final void method(java.util.List<java.lang.Exception> exceptions, java.io.PrintWriter pw) throws Throwable {
        for (Exception exception : exceptions) {
            pw.print("\t");
            pw.println(exception);
            for (StackTraceElement element : exception.getStackTrace()) {
                pw.print("\t\tat ");
                pw.println(element);
            }
        }
    }
}

