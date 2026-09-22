import java.net.ServerSocket;

public class TplClass7262 {

    private static final void method(java.net.ServerSocket ss, int ssLocalPort, int failCount) throws Throwable {
        if (ssLocalPort != ss.getLocalPort()) {
            failCount++;
        }
    }
}

