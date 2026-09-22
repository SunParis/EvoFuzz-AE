import java.net.URLDecoder;

public class TplClass7660 {

    private static final void method(boolean except) throws Throwable {
        try {
            URLDecoder ud = new java.net.URLDecoder();
            String s = ud.decode("%-1", "iso-8859-1");
        } catch (Exception e) {
            except = true;
        }
    }
}

