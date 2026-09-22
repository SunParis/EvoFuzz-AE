import java.io.IOException;
import java.net.SocketException;
import java.nio.channels.SocketChannel;

public class TplClass6450 {

    private static final void method(java.nio.channels.SocketChannel sc, java.lang.String osName) throws Throwable {
        try {
            sc.socket().sendUrgentData(0);
        } catch (IOException ex) {
            if (osName.contains("linux")) {
                if (!ex.getMessage().contains("Socket buffer full")) {
                }
            } else if (osName.contains("os x") || osName.contains("mac")) {
                if (!ex.getMessage().equals("No buffer space available")) {
                }
            } else if (osName.contains("windows")) {
                if (!(ex instanceof SocketException)) {
                } else if (!ex.getMessage().contains("Resource temporarily unavailable")) {
                }
            } else {
            }
        }
    }
}

