import java.io.IOException;
import java.net.SocketException;

public class TplClass6442 {

    private static final void method(java.lang.String osName, java.io.IOException ex) throws Throwable {
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

