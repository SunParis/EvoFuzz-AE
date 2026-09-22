import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.io.InterruptedIOException;
import java.net.Socket;

public class TplClass5305 {

    private static final void method(java.io.InputStream is, java.net.Socket soc, java.net.Socket soc1) throws Throwable {
        try {
            is = soc.getInputStream();
            is.read();
        } catch (InterruptedIOException e) {
            try {
                if (!(e instanceof java.net.SocketTimeoutException))
                    ;
            } catch (NoClassDefFoundError e1) {
            }
        } finally {
            soc.close();
            soc1.close();
        }
    }
}

