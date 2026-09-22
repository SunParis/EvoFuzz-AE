import java.io.IOException;
import java.net.SocketException;

public class TplClass6444 {

    private static final void method(java.lang.String osName, java.io.IOException ex) throws Throwable {
        if (osName.contains("os x") || osName.contains("mac")) {
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

