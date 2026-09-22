import java.io.OutputStream;

public class TplClass5995 {

    private static final void method(java.io.OutputStream out) throws Throwable {
        // see EOF on input, it is necessary to close stdin
        if (out != null) {
            try {
                out.close();
            } catch (Exception e) {
            }
        }
    }
}

