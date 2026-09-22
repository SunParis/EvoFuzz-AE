import java.net.DatagramPacket;

public class TplClass5294 {

    private static final void method() throws Throwable {
        boolean error = true;
        byte[] buf = new byte[128];
        try {
            /* length greater than buffer length */
            new DatagramPacket(buf, 256);
        } catch (IllegalArgumentException e) {
            /* correct exception */
            error = false;
        }
        if (error) {
        }
    }
}

