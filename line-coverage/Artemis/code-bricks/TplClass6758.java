import java.net.ServerSocket;
import java.io.InterruptedIOException;

public class TplClass6758 {

    private static final void method(java.net.ServerSocket sock, boolean success) throws Throwable {
        try {
            sock.setSoTimeout(2);
            sock.accept();
        } catch (InterruptedIOException e) {
            success = true;
        } finally {
            sock.close();
        }
    }
}

