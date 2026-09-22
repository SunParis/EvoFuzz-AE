import java.net.DatagramPacket;

public class TplClass5285 {

    private static final void method(boolean error, byte[] buf) throws Throwable {
        try {
            /* length lesser than buffer length */
            new DatagramPacket(buf, -128);
        } catch (IllegalArgumentException e) {
            /* correct exception */
            error = false;
        }
    }
}

