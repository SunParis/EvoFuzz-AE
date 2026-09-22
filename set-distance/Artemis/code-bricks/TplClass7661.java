import java.net.URLDecoder;

public class TplClass7661 {

    private static final void method() throws Throwable {
        boolean except = false;
        try {
            URLDecoder ud = new java.net.URLDecoder();
            String s = ud.decode("%-1", "iso-8859-1");
        } catch (Exception e) {
            except = true;
        }
        if (!except)
            ;
    }
}

