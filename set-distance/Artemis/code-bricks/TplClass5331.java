import java.net.InetAddress;

public class TplClass5331 {

    private static final void method(java.lang.String[] goodlinklocal, int i) throws Throwable {
        for (i = 0; i < goodlinklocal.length; i++) {
            InetAddress ia = InetAddress.getByName(goodlinklocal[i]);
            if (!ia.isLinkLocalAddress()) {
            } else {
            }
        }
    }
}

