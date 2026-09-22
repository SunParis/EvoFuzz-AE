import java.nio.channels.DatagramChannel;

public class TplClass6528 {

    private static final void method() throws Throwable {
        try (DatagramChannel dc = DatagramChannel.open()) {
            dc.bind(null);
        }
    }
}

