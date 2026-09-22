import java.net.InetAddress;
import java.util.BitSet;
import java.net.UnknownHostException;

public class TplClass7006 {

    private static final void method() throws Throwable {
        String[] malformedIPv4s = { "192.168.1.220..." };
        BitSet expectedExceptions = new BitSet(malformedIPv4s.length);
        expectedExceptions.clear();
        for (int i = 0; i < malformedIPv4s.length; i++) {
            try {
                InetAddress.getAllByName(malformedIPv4s[i]);
            } catch (UnknownHostException e) {
                expectedExceptions.set(i);
            }
        }
        for (int i = 0; i < malformedIPv4s.length; i++) {
            if (!expectedExceptions.get(i)) {
            }
        }
        if (expectedExceptions.cardinality() != malformedIPv4s.length) {
        }
    }
}

