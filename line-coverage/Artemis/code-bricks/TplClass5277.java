import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class TplClass5277 {

    private static final void method(java.lang.String[] msgs, java.net.DatagramPacket dp, int port, java.net.DatagramSocket ds) throws Throwable {
        for (int i = 0; i < msgs.length; i++) {
            ds.send(new DatagramPacket(msgs[i].getBytes(), msgs[i].length(), InetAddress.getLocalHost(), port));
            ds.receive(dp);
            if (!msgs[i].equals(new String(dp.getData(), dp.getOffset(), dp.getLength()))) {
            }
        }
    }
}

