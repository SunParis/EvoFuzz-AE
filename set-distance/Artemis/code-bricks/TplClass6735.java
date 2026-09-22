import java.net.InetAddress;
import java.net.Socket;

public class TplClass6735 {

    private static final void method(java.net.InetAddress addr, long timeWritten, int port) throws Throwable {
        try {
            byte[] b = new byte[12];
            Socket s = new Socket(addr, port);
            Thread.yield();
            timeWritten = System.currentTimeMillis();
            s.getOutputStream().write(b, 0, 12);
            s.close();
        } catch (Exception e) {
        }
    }
}

