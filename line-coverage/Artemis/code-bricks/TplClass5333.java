import java.net.InetAddress;

public class TplClass5333 {

    private static final void method(java.lang.String[] goodsitelocal, int i) throws Throwable {
        for (i = 0; i < goodsitelocal.length; i++) {
            InetAddress ia = InetAddress.getByName(goodsitelocal[i]);
            if (!ia.isSiteLocalAddress()) {
            }
        }
    }
}

