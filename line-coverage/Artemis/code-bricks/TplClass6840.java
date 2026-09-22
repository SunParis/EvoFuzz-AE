import java.net.Socket;
import java.net.ServerSocket;

public class TplClass6840 {

    private static final void method(java.net.ServerSocket svr, java.net.Socket s) throws Throwable {
        while (true) {
            s = svr.accept();
            s.close();
        }
    }
}

