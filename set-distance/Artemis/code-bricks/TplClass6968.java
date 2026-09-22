import java.net.UnknownHostException;
import java.net.InetAddress;

public class TplClass6968 {

    private static final void method(java.net.InetAddress a) throws Throwable {
        try {
            a = InetAddress.getByName("foo.bar");
        } catch (UnknownHostException e) {
            String s = e.getMessage();
            if (s.contains("foo.bar: foo.bar") || s.contains("unknown error"))
                ;
        }
    }
}

