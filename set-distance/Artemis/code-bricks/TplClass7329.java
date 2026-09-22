import java.security.cert.CRLException;

public class TplClass7329 {

    private static final void method(java.lang.String MSG, java.lang.Exception cause, java.security.cert.CRLException crle) throws Throwable {
        if (!crle.getMessage().equals(MSG) || !crle.getCause().equals(cause)) {
        }
    }
}

