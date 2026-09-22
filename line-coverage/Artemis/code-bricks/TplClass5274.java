import java.net.DatagramPacket;

public class TplClass5274 {

    private static final void method() throws Throwable {
        boolean exception = false;
        try {
            final int offset = Integer.MAX_VALUE;
            final int length = 1;
            new DatagramPacket(new byte[1024], offset, length);
        } catch (IllegalArgumentException e) {
            exception = true;
        }
        if (!exception)
            ;
    }
}

