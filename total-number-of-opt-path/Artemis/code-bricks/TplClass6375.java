import java.io.IOException;
import java.net.InetAddress;
import java.nio.channels.SocketChannel;
import java.nio.channels.ServerSocketChannel;
import java.net.InetSocketAddress;

public class TplClass6375 {

    private static final void method() throws Throwable {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(0));
        InetAddress lh = InetAddress.getLocalHost();
        final SocketChannel sc = SocketChannel.open();
        final InetSocketAddress isa = new InetSocketAddress(lh, ssc.socket().getLocalPort());
        // establish connection in another thread
        Runnable connector = new Runnable() {

            public void run() {
                try {
                    sc.connect(isa);
                } catch (IOException ioe) {
                }
            }
        };
        Thread thr = new Thread(connector);
        thr.start();
        // terminate
        do {
            try {
                thr.join();
            } catch (InterruptedException x) {
            }
        } while (thr.isAlive());
        // check connection is established
        if (!sc.isConnected()) {
        }
        // a thread that no longer exists
        sc.close();
        // clean-up
        ssc.accept().close();
        ssc.close();
    }
}

