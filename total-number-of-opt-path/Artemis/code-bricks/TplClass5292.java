import java.net.DatagramPacket;

public class TplClass5292 {

    private static final void method() throws Throwable {
        boolean error = true;
        byte[] buf = new byte[128];
        try {
            /* length lesser than buffer length */
            new DatagramPacket(buf, -128);
        } catch (IllegalArgumentException e) {
            /* correct exception */
            error = false;
        }
        if (error) {
        }
    }
}

