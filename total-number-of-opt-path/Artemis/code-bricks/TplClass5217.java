import java.net.ServerSocket;

public class TplClass5217 {

    private static final void method(int port, java.net.ServerSocket soc) throws Throwable {
        soc = new ServerSocket(port, Integer.MAX_VALUE);
        port = soc.getLocalPort();
    }
}

