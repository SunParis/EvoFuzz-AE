import java.net.ServerSocket;
import java.io.InterruptedIOException;

public class TplClass6759 {

    private static final void method() throws Throwable {
        boolean success = false;
        ServerSocket sock = new ServerSocket(0);
        try {
            sock.setSoTimeout(2);
            sock.accept();
        } catch (InterruptedIOException e) {
            success = true;
        } finally {
            sock.close();
        }
        if (!success)
            ;
    }
}

