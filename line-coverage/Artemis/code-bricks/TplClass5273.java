import java.net.DatagramPacket;

public class TplClass5273 {

    private static final void method(boolean exception) throws Throwable {
        try {
            final int offset = Integer.MAX_VALUE;
            final int length = 1;
            new DatagramPacket(new byte[1024], offset, length);
        } catch (IllegalArgumentException e) {
            exception = true;
        }
    }
}

