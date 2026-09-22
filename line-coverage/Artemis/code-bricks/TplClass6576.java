import java.nio.channels.DatagramChannel;
import java.nio.ByteBuffer;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.InetAddress;

public class TplClass6576 {

    private static final void method() throws Throwable {
        // clients
        DatagramChannel dc1 = DatagramChannel.open();
        DatagramChannel dc2 = DatagramChannel.open();
        // bind server to any port
        DatagramChannel dc3 = DatagramChannel.open();
        dc3.socket().bind((SocketAddress) null);
        // get server address
        InetAddress lh = InetAddress.getLocalHost();
        InetSocketAddress isa = new InetSocketAddress(lh, dc3.socket().getLocalPort());
        ByteBuffer bb = ByteBuffer.allocateDirect(100);
        bb.put("Dia duit!".getBytes());
        bb.flip();
        // packet 1 from dc1
        dc1.send(bb, isa);
        // packet 2 from dc1
        dc1.send(bb, isa);
        // packet 3 from dc1
        dc2.send(bb, isa);
        // receive 3 packets
        dc3.socket().setSoTimeout(1000);
        ByteBuffer rb = ByteBuffer.allocateDirect(100);
        SocketAddress[] sa = new SocketAddress[3];
        for (int i = 0; i < 3; i++) {
            sa[i] = dc3.receive(rb);
            rb.clear();
        }
        dc1.close();
        dc2.close();
        dc3.close();
        if (!sa[0].equals(sa[1])) {
        }
        if (sa[1].equals(sa[2])) {
        }
    }
}

