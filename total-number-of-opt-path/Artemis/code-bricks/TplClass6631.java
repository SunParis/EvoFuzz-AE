import java.nio.channels.SocketChannel;

public class TplClass6631 {

    private static final void method(java.nio.channels.SocketChannel[] channels, int CHANNELS_PER_THREAD) throws Throwable {
        // clean-up
        for (int i = 0; i < CHANNELS_PER_THREAD; i++) {
            channels[i].close();
        }
    }
}

