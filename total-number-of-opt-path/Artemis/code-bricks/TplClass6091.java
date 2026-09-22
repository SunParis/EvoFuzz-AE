import java.net.InetSocketAddress;

public class TplClass6091 {

    private static final void method() throws Throwable {
        InetSocketAddress a = InetSocketAddress.createUnresolved("unresolved", 1234);
        if (!a.isUnresolved())
            ;
    }
}

