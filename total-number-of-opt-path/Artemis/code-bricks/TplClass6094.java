import java.net.InetSocketAddress;

public class TplClass6094 {

    private static final void method() throws Throwable {
        InetSocketAddress addr = new InetSocketAddress("192.168.1.1", 12345);
        String s = addr.getHostString();
        if (!s.equals("192.168.1.1"))
            ;
        addr = new InetSocketAddress("localhost", 12345);
        s = addr.getHostString();
        if (!s.equals("localhost"))
            ;
    }
}

