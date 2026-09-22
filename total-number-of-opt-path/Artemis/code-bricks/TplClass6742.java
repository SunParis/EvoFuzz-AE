import java.net.Socket;

public class TplClass6742 {

    private static final void method() throws Throwable {
        try (Socket soc = new Socket()) {
            soc.bind(null);
            if (!soc.isBound())
                ;
            if (soc.getLocalPort() <= 0)
                ;
            if (soc.getLocalAddress() == null)
                ;
        }
    }
}

