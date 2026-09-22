import java.io.OutputStream;

public class TplClass5994 {

    private static final void method(java.io.OutputStream out) throws Throwable {
        if (!System.out.equals(out) && !System.err.equals(out)) {
            // Note: in order to get an exec'd java process to
            // see EOF on input, it is necessary to close stdin
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                }
            }
        }
    }
}

