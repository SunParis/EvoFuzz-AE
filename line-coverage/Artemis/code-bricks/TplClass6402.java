import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TplClass6402 {

    private static final void method(java.nio.channels.SocketChannel server, java.nio.channels.SocketChannel client) throws Throwable {
        Runnable sender = new Runnable() {

            public void run() {
                try {
                    for (int i = 0; i < 256; i++) client.socket().sendUrgentData(i);
                } catch (Exception ioe) {
                }
            }
        };
        Thread thr = new Thread(sender);
        thr.start();
        ByteBuffer bb = ByteBuffer.allocate(256);
        while (bb.hasRemaining()) {
            if (server.read(bb) < 0)
                ;
        }
        bb.flip();
        byte expect = 0;
        while (bb.hasRemaining()) {
            if (bb.get() != expect)
                ;
            expect++;
        }
        thr.join();
    }
}

