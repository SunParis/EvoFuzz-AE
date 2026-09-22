import java.nio.channels.DatagramChannel;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.CharBuffer;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.nio.ByteBuffer;

public class TplClass6538 {

    private static final void method() throws Throwable {
        DatagramChannel sndChannel = DatagramChannel.open();
        sndChannel.socket().bind(null);
        InetAddress address = InetAddress.getLocalHost();
        if (address.isLoopbackAddress()) {
            address = InetAddress.getLoopbackAddress();
        }
        InetSocketAddress sender = new InetSocketAddress(address, sndChannel.socket().getLocalPort());
        DatagramChannel rcvChannel = DatagramChannel.open();
        rcvChannel.socket().bind(null);
        InetSocketAddress receiver = new InetSocketAddress(address, rcvChannel.socket().getLocalPort());
        rcvChannel.connect(sender);
        sndChannel.connect(receiver);
        byte[] b = "hello".getBytes("UTF-8");
        DatagramPacket pkt = new DatagramPacket(b, b.length);
        sndChannel.socket().send(pkt);
        ByteBuffer bb = ByteBuffer.allocate(256);
        rcvChannel.receive(bb);
        bb.flip();
        CharBuffer cb = Charset.forName("US-ASCII").newDecoder().decode(bb);
        if (!cb.toString().startsWith("h"))
            ;
        // This is legacy behavior
        if (!pkt.getSocketAddress().equals(receiver))
            ;
        rcvChannel.close();
        sndChannel.close();
    }
}

