import java.net.InetAddress;

public class TplClass5339 {

    private static final void method() throws Throwable {
        String[] goodlinklocal = { "fe80::a00:20ff:feae:45c9", "fe80::", "feb0::" };
        String[] badlinklocal = { "fec0::", "fe70::", "ff00::" };
        String[] goodsitelocal = { "fec0::a00:20ff:feae:45c9", "feff::" };
        String[] badsitelocal = { "fe80::", "fe00::", "ffc0::" };
        int i;
        for (i = 0; i < goodlinklocal.length; i++) {
            InetAddress ia = InetAddress.getByName(goodlinklocal[i]);
            if (!ia.isLinkLocalAddress()) {
            } else {
            }
        }
        for (i = 0; i < badlinklocal.length; i++) {
            InetAddress ia = InetAddress.getByName(badlinklocal[i]);
            if (ia.isLinkLocalAddress()) {
            }
        }
        for (i = 0; i < goodsitelocal.length; i++) {
            InetAddress ia = InetAddress.getByName(goodsitelocal[i]);
            if (!ia.isSiteLocalAddress()) {
            }
        }
        for (i = 0; i < badsitelocal.length; i++) {
            InetAddress ia = InetAddress.getByName(badsitelocal[i]);
            if (ia.isSiteLocalAddress()) {
            }
        }
    }
}

