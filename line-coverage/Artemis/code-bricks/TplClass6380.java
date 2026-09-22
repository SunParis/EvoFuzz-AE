import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;
import java.net.InetSocketAddress;

public class TplClass6380 {

    private static final void method() throws Throwable {
        InetSocketAddress iAddr = new InetSocketAddress("nosuchhostname", 5182);
        try {
            SocketChannel channel = SocketChannel.open();
            channel.socket().connect(iAddr, 30000);
        } catch (UnknownHostException x) {
            // Expected result
        }
        try {
            SocketChannel chan1 = SocketChannel.open();
            chan1.socket().bind(new InetSocketAddress(0));
            chan1.socket().bind(new InetSocketAddress(0));
        } catch (IOException e) {
            // Expepected result
        }
    }
}

