import java.io.IOException;
import java.net.Socket;

public class TplClass6831 {

    private static final void method() throws Throwable {
        try {
            Socket socket = new Socket();
            boolean on = socket.getTcpNoDelay();
            boolean opposite = on ? false : true;
            socket.setTcpNoDelay(opposite);
            boolean noDelay = socket.getTcpNoDelay();
            if (noDelay != opposite)
                ;
        } catch (IOException e) {
        }
    }
}

