import java.io.IOException;

public class TplClass6085 {

    private static final void method(java.io.IOException ioe) throws Throwable {
        /*
             * Check that the exception text doesn't indicate the
             * socket is closed. In tiger we should be able to
             * replace this by catching a more specific exception.
             */
        String text = ioe.getMessage();
        if (text.toLowerCase().indexOf("closed") >= 0) {
        }
    }
}

