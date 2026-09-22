import java.nio.ByteOrder;
import java.net.InetSocketAddress;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class TplClass6554 {

    private static final void method(int port) throws Throwable {
        DatagramChannel dc = DatagramChannel.open();
        ByteBuffer bb = ByteBuffer.allocateDirect(12);
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.putInt(1).putLong(1);
        bb.flip();
        InetAddress address = InetAddress.getLocalHost();
        InetSocketAddress isa = new InetSocketAddress(address, port);
        dc.connect(isa);
        dc.write(bb);
    }
}

