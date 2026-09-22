import java.nio.ByteBuffer;
import java.net.InetSocketAddress;
import java.net.InetAddress;
import java.nio.channels.SocketChannel;

public class TplClass5443 {

    private static final void method(java.lang.String msg, int reply_port) throws Throwable {
        InetSocketAddress isa = new InetSocketAddress(InetAddress.getLocalHost(), reply_port);
        SocketChannel sc = SocketChannel.open(isa);
        byte[] b = msg.getBytes("UTF-8");
        ByteBuffer bb = ByteBuffer.wrap(b);
        sc.write(bb);
        sc.close();
    }
}

