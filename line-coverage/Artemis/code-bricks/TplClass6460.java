import java.io.IOException;
import java.nio.channels.ServerSocketChannel;

public class TplClass6460 {

    private static final void method(java.nio.channels.ServerSocketChannel ssc) throws Throwable {
        while (ssc.isOpen()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
        }
        try {
            ssc.close();
        } catch (IOException ex) {
        }
    }
}

