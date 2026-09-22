import java.io.IOException;
import java.net.SocketException;

public class TplClass6447 {

    private static final void method(java.io.IOException ex) throws Throwable {
        if (!(ex instanceof SocketException)) {
        } else if (!ex.getMessage().contains("Resource temporarily unavailable")) {
        }
    }
}

