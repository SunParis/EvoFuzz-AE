import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;

public class TplClass6842 {

    private static final void method(java.net.ServerSocket svr, java.net.Socket s) throws Throwable {
        try {
            while (true) {
                s = svr.accept();
                s.close();
            }
        } catch (IOException e) {
        }
    }
}

