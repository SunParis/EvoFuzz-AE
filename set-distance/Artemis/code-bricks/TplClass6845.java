import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;

public class TplClass6845 {

    private static final void method(java.net.ServerSocket svr) throws Throwable {
        Socket s;
        try {
            while (true) {
                s = svr.accept();
                s.close();
            }
        } catch (IOException e) {
        }
    }
}

