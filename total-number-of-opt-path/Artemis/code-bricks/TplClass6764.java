import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;

public class TplClass6764 {

    private static final void method(boolean failed, java.net.Socket s1, java.lang.String mesg, java.net.Socket s2) throws Throwable {
        OutputStream os = s1.getOutputStream();
        os.write("This is a message".getBytes("US-ASCII"));
        InputStream in = s2.getInputStream();
        s2.shutdownInput();
        if (in.available() != 0) {
            failed = true;
        }
        byte[] ba = new byte[2];
        if (in.read() != -1 || in.read(ba) != -1 || in.read(ba, 0, ba.length) != -1) {
            failed = true;
        }
    }
}

