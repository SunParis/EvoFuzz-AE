import java.io.PrintWriter;

public class TplClass5176 {

    private static final void method(java.lang.Exception exception, java.io.PrintWriter pw) throws Throwable {
        pw.print("\t");
        pw.println(exception);
        for (StackTraceElement element : exception.getStackTrace()) {
            pw.print("\t\tat ");
            pw.println(element);
        }
    }
}

