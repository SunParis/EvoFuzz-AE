import java.util.Random;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TplClass6400 {

    private static final void method(java.util.Random rand, java.nio.channels.SocketChannel server, java.nio.channels.SocketChannel client) throws Throwable {
        ByteBuffer bb = ByteBuffer.allocate(100);
        for (int i = 0; i < 1000; i++) {
            int b1 = -127 + rand.nextInt(384);
            client.socket().sendUrgentData(b1);
            bb.clear();
            if (server.read(bb) != 1)
                ;
            bb.flip();
            byte b2 = bb.get();
            if ((byte) b1 != b2)
                ;
        }
    }
}

