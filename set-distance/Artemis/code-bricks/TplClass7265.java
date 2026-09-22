import java.net.ServerSocket;

public class TplClass7265 {

    private static final void method(java.net.ServerSocket ss, int failCount) throws Throwable {
        if (!ss.isBound()) {
            failCount++;
        }
    }
}

