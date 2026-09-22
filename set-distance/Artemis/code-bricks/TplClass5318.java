import java.net.Socket;
import java.io.OutputStream;
import java.net.ServerSocket;

public class TplClass5318 {

    private static final void method(java.net.ServerSocket serverSocket) throws Throwable {
        try {
            Socket socket = serverSocket.accept();
            OutputStream os = socket.getOutputStream();
            os.write(85);
            os.flush();
            socket.close();
        } finally {
            serverSocket.close();
        }
    }
}

