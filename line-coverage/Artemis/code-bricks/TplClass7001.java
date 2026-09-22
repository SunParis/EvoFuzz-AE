import java.net.InetAddress;
import java.util.BitSet;
import java.net.UnknownHostException;

public class TplClass7001 {

    private static final void method(java.util.BitSet expectedExceptions, java.lang.String[] malformedIPv4s) throws Throwable {
        for (int i = 0; i < malformedIPv4s.length; i++) {
            try {
                InetAddress.getAllByName(malformedIPv4s[i]);
            } catch (UnknownHostException e) {
                expectedExceptions.set(i);
            }
        }
    }
}

