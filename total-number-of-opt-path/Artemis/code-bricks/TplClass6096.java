import java.net.InetSocketAddress;

public class TplClass6096 {

    private static final void method() throws Throwable {
        InetSocketAddress addr1 = InetSocketAddress.createUnresolved("unresolveable", 10);
        InetSocketAddress addr2 = InetSocketAddress.createUnresolved("UNRESOLVEABLE", 10);
        if (!(addr1.equals(addr2))) {
        }
    }
}

