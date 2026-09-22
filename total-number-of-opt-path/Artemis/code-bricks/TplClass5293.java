import java.net.DatagramPacket;

public class TplClass5293 {

    private static final void method(byte[] buf) throws Throwable {
        /* length lesser than buffer length */
        new DatagramPacket(buf, -128);
    }
}

