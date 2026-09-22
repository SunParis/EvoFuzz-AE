import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;
import java.net.InetSocketAddress;
import java.nio.CharBuffer;
import java.net.InetAddress;
import java.nio.ByteBuffer;

public class TplClass6536 {

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
        ByteBuffer bb = ByteBuffer.allocate(256);
        bb.put("hello".getBytes());
        bb.flip();
        int sent = sndChannel.send(bb, receiver);
        bb.clear();
        rcvChannel.receive(bb);
        bb.flip();
        CharBuffer cb = Charset.forName("US-ASCII").newDecoder().decode(bb);
        if (!cb.toString().startsWith("h"))
            ;
        rcvChannel.close();
        sndChannel.close();
    }
}

