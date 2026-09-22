import java.net.InetAddress;
import java.util.BitSet;
import java.net.UnknownHostException;

public class TplClass7005 {

    private static final void method(java.util.BitSet expectedExceptions, java.lang.String[] malformedIPv4s, int i) throws Throwable {
        try {
            InetAddress.getAllByName(malformedIPv4s[i]);
        } catch (UnknownHostException e) {
            expectedExceptions.set(i);
        }
    }
}

