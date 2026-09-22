import java.net.InetAddress;

public class TplClass6533 {

    private static final void method(java.net.InetAddress address) throws Throwable {
        if (address.isLoopbackAddress()) {
            address = InetAddress.getLoopbackAddress();
        }
    }
}

