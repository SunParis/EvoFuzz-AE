import java.net.InetAddress;

public class TplClass5340 {

    private static final void method(java.lang.String[] goodlinklocal, int i) throws Throwable {
        InetAddress ia = InetAddress.getByName(goodlinklocal[i]);
        if (!ia.isLinkLocalAddress()) {
        } else {
        }
    }
}

