import java.net.Socket;

public class TplClass6832 {

    private static final void method() throws Throwable {
        Socket socket = new Socket();
        boolean on = socket.getTcpNoDelay();
        boolean opposite = on ? false : true;
        socket.setTcpNoDelay(opposite);
        boolean noDelay = socket.getTcpNoDelay();
        if (noDelay != opposite)
            ;
    }
}

