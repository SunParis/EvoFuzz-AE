import java.io.PrintWriter;

public class TplClass5170 {

    private static final void method(java.lang.Exception exception, java.io.PrintWriter pw) throws Throwable {
        for (StackTraceElement element : exception.getStackTrace()) {
            pw.print("\t\tat ");
            pw.println(element);
        }
    }
}

