import java.net.UnknownHostException;
import java.net.InetAddress;

public class TplClass6969 {

    private static final void method() throws Throwable {
        InetAddress a;
        try {
            a = InetAddress.getByName("foo.bar");
        } catch (UnknownHostException e) {
            String s = e.getMessage();
            if (s.contains("foo.bar: foo.bar") || s.contains("unknown error"))
                ;
        }
    }
}

