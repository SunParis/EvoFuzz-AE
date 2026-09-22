import java.nio.ByteBuffer;
import java.util.Random;
import java.nio.channels.SocketChannel;

public class TplClass6404 {

    private static final void method(java.util.Random rand, java.nio.channels.SocketChannel server, java.nio.channels.SocketChannel client) throws Throwable {
        final int STOP = rand.nextInt(256);
        Runnable reader = new Runnable() {

            public void run() {
                ByteBuffer bb = ByteBuffer.allocate(100);
                try {
                    int n = server.read(bb);
                    if (n != 1) {
                        String msg = (n < 0) ? "Unexpected EOF" : "One byte expected";
                    }
                    bb.flip();
                    if (bb.get() != (byte) STOP)
                        ;
                    bb.flip();
                    server.write(bb);
                } catch (Exception ioe) {
                }
            }
        };
        Thread thr = new Thread(reader);
        thr.start();
        // "stop" server
        client.socket().sendUrgentData(STOP);
        // wait for server reply
        ByteBuffer bb = ByteBuffer.allocate(100);
        int n = client.read(bb);
        if (n != 1)
            ;
        bb.flip();
        if (bb.get() != (byte) STOP)
            ;
        thr.join();
    }
}

