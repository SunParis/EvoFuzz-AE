import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TplClass7258 {

    private static final void method(java.net.ServerSocket ss, int REPS) throws Throwable {
        try {
            for (int i = 0; i < REPS; i++) {
                (new Socket("localhost", ss.getLocalPort())).close();
            }
        } catch (IOException e) {
        }
    }
}

