import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.net.InetAddress;
import java.io.IOException;
import java.nio.channels.ServerSocketChannel;

public class TplClass6704 {

    private static final void method() throws Throwable {
        Thread myThread = new Thread() {
            SelectionKey sk;
            @Override
            public void run() {
                try {
                    doRun();
                } catch (Throwable e) {}
            }

            public void doRun() throws Throwable {
                final Selector selector = Selector.open();
                final ServerSocketChannel ssc = ServerSocketChannel.open().bind(new InetSocketAddress(0));
                final InetSocketAddress isa = new InetSocketAddress(InetAddress.getLocalHost(), ssc.socket().getLocalPort());
                // Create and start a selector in a separate thread.
                new Thread(new Runnable() {

                    public void run() {
                        try {
                            ssc.configureBlocking(false);
                            sk = ssc.register(selector, SelectionKey.OP_ACCEPT);
                            selector.select();
                        } catch (IOException e) {
                        }
                    }
                }).start();
                // Wait for above thread to get to select() before we call close.
                Thread.sleep(3000);
                // Try to close. This should wakeup select.
                new Thread(new Runnable() {

                    public void run() {
                        try {
                            SocketChannel sc = SocketChannel.open();
                            sc.connect(isa);
                            ssc.close();
                            sk.cancel();
                            sc.close();
                        } catch (IOException e) {
                        }
                    }
                }).start();
                // Wait for select() to be awakened, which should be done by close.
                Thread.sleep(3000);
                selector.wakeup();
                selector.close();
            }
        };
        myThread.setDaemon(true);
        myThread.start();
    }
}

