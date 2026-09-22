import java.net.SocketTimeoutException;
import java.io.InterruptedIOException;
import java.net.ServerSocket;

public class TplClass5307 {

    private static final void method(java.net.ServerSocket srv) throws Throwable {
        try {
            srv.accept();
        } catch (InterruptedIOException e) {
            try {
                if (!(e instanceof java.net.SocketTimeoutException))
                    ;
            } catch (NoClassDefFoundError e1) {
            }
        } finally {
            srv.close();
        }
    }
}

