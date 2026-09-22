import java.net.ServerSocket;
import java.net.Socket;

public class TplClass6799 {

    private static final void method(java.net.ServerSocket ss) throws Throwable {
        try (Socket s = new Socket("localhost", ss.getLocalPort());
            Socket peer = ss.accept()) {
            for (int i = 0; i < 1000000; i++) {
                // buggy JDK will run out of memory in this loop
                s.getOutputStream();
                // test InputStream also while we're here
                s.getInputStream();
                if (i % 100000 == 0)
                    ;
            }
        }
    }
}

