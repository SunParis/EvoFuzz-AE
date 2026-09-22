import java.net.ServerSocket;

public class TplClass5213 {

    private static final void method(int port, java.net.ServerSocket soc) throws Throwable {
        try {
            soc = new ServerSocket(port, Integer.MAX_VALUE);
            port = soc.getLocalPort();
        } catch (Exception e) {
        }
    }
}

