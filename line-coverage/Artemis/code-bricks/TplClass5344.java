import java.net.UnknownHostException;
import java.net.InetAddress;

public class TplClass5344 {

    private static final void method() throws Throwable {
        try {
            InetAddress addr = InetAddress.getByName(":");
        } catch (java.net.UnknownHostException uhe) {
            // what we expect. So everything is OK
        } catch (Exception e) {
        }
    }
}

