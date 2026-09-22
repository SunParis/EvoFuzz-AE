import java.net.DatagramPacket;

public class TplClass5286 {

    private static final void method(boolean error, byte[] buf) throws Throwable {
        try {
            /* length greater than buffer length */
            new DatagramPacket(buf, 256);
        } catch (IllegalArgumentException e) {
            /* correct exception */
            error = false;
        }
    }
}

