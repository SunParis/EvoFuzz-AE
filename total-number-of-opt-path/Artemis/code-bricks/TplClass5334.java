import java.net.InetAddress;

public class TplClass5334 {

    private static final void method(int i, java.lang.String[] badsitelocal) throws Throwable {
        for (i = 0; i < badsitelocal.length; i++) {
            InetAddress ia = InetAddress.getByName(badsitelocal[i]);
            if (ia.isSiteLocalAddress()) {
            }
        }
    }
}

