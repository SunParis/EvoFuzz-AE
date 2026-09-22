import java.net.DatagramPacket;

public class TplClass5284 {

    private static final void method(boolean error) throws Throwable {
        try {
            new DatagramPacket(null, 100);
        } catch (NullPointerException e) {
            /* correct exception */
            error = false;
        }
    }
}

