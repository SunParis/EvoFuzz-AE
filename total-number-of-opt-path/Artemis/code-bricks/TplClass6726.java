import java.io.InputStream;
import java.net.Socket;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.io.InterruptedIOException;

public class TplClass6726 {

    private static final void method(java.io.InputStream is, java.io.OutputStream os, java.net.ServerSocket srv, java.net.Socket soc, java.net.Socket soc1) throws Throwable {
        try {
            is = soc.getInputStream();
            os = soc1.getOutputStream();
            is.read();
        } catch (InterruptedIOException e) {
        } finally {
            soc.close();
            soc1.close();
            srv.close();
        }
    }
}

