import java.io.FileWriter;
import java.net.InetAddress;
import java.net.Inet6Address;
import java.io.BufferedReader;

public class TplClass6974 {

    private static final void method(java.lang.String[] args) throws Throwable {
        String hostName = "fec0::1:a00:20ff:feed:b08d";
        BufferedReader in = null;
        FileWriter fw = null;
        String inString = " ";
        if (args.length > 0)
            hostName = args[0];
        InetAddress addr = InetAddress.getByName(hostName);
        if (!addr.isReachable(10000)) {
        } else {
        }
    }
}

