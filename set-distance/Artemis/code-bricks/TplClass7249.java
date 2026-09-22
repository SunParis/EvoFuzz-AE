import java.net.ServerSocket;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.SocketTimeoutException;

public class TplClass7249 {

    private static final void method() throws Throwable {
        try (ServerSocket ss = new ServerSocket(0)) {
            ss.setSoTimeout(2000);
            ss.accept();
        } catch (SocketTimeoutException x) {
            // expected
        } catch (IOException x) {
        }
    }
}

