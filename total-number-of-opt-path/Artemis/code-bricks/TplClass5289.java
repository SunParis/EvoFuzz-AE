import java.net.DatagramPacket;

public class TplClass5289 {

    private static final void method() throws Throwable {
        boolean error = true;
        try {
            new DatagramPacket(null, 100);
        } catch (NullPointerException e) {
            /* correct exception */
            error = false;
        }
        if (error) {
        }
    }
}

