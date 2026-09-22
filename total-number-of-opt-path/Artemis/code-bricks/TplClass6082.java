import java.io.IOException;
import java.net.Socket;

public class TplClass6082 {

    private static final void method(java.net.Socket server, java.net.Socket client) throws Throwable {
        try {
            client.getOutputStream().write(new byte[1000000]);
        } catch (IOException ioe) {
            /*
             * Check that the exception text doesn't indicate the
             * socket is closed. In tiger we should be able to
             * replace this by catching a more specific exception.
             */
            String text = ioe.getMessage();
            if (text.toLowerCase().indexOf("closed") >= 0) {
            }
        } finally {
            server.close();
        }
    }
}

