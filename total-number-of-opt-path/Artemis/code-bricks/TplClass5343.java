import java.net.InetAddress;

public class TplClass5343 {

    private static final void method(int i, java.lang.String[] badsitelocal) throws Throwable {
        InetAddress ia = InetAddress.getByName(badsitelocal[i]);
        if (ia.isSiteLocalAddress()) {
        }
    }
}

