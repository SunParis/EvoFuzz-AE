import java.net.DatagramPacket;

public class TplClass5278 {

    private static final void method(java.lang.String[] msgs, int i, java.net.DatagramPacket dp) throws Throwable {
        if (!msgs[i].equals(new String(dp.getData(), dp.getOffset(), dp.getLength()))) {
        }
    }
}

