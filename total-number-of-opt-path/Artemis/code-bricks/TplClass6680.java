import java.nio.channels.ServerSocketChannel;
import java.net.SocketTimeoutException;
import java.nio.channels.SocketChannel;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.ServerSocket;

public class TplClass6680 {

    private static final void method(boolean done) throws Throwable {
        Runnable r = new Runnable() {
            boolean alreadyDone = true;
            public void run() {
                while (!alreadyDone) {
                    System.gc();
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                }
            }
        };
        try {
            // Create a server socket that will open and accept on loopback connection
            ServerSocketChannel ssc = ServerSocketChannel.open();
            final ServerSocket ss = ssc.socket();
            ss.bind(new InetSocketAddress(0));
            int localPort = ss.getLocalPort();
            SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", localPort));
            // Create a thread to try and cause the GC to run
            Thread t = new Thread(r);
            t.start();
            byte[] buffer = new byte[500];
            Socket socket = channel.socket();
            // The timeout must be set
            socket.setSoTimeout(10000);
            // to trigger this bug
            try {
                socket.getInputStream().read(buffer);
            } catch (java.net.SocketTimeoutException ste) {
                // no java.nio.channels.ClosedSelectorException
            }
        } finally {
            done = true;
        }
    }
}

