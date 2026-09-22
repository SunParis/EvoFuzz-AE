import java.net.InetAddress;

public class TplClass5342 {

    private static final void method(java.lang.String[] goodsitelocal, int i) throws Throwable {
        InetAddress ia = InetAddress.getByName(goodsitelocal[i]);
        if (!ia.isSiteLocalAddress()) {
        }
    }
}

