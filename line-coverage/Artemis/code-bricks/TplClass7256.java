import java.net.ServerSocket;
import java.net.Socket;

public class TplClass7256 {

    private static final void method(java.net.ServerSocket ss, int REPS) throws Throwable {
        for (int i = 0; i < REPS; i++) {
            (new Socket("localhost", ss.getLocalPort())).close();
        }
    }
}

