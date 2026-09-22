import java.net.Socket;
import java.io.InputStream;

public class TplClass6763 {

    private static final void method(boolean failed, java.io.InputStream in, java.lang.String mesg, java.net.Socket s2, byte[] ba) throws Throwable {
        if (in.read() != -1 || in.read(ba) != -1 || in.read(ba, 0, ba.length) != -1) {
            failed = true;
        }
    }
}

