import java.net.InetAddress;

public class TplClass5332 {

    private static final void method(java.lang.String[] badlinklocal, int i) throws Throwable {
        for (i = 0; i < badlinklocal.length; i++) {
            InetAddress ia = InetAddress.getByName(badlinklocal[i]);
            if (ia.isLinkLocalAddress()) {
            }
        }
    }
}

