import java.security.Signature;
import java.security.KeyPair;
import java.security.SignedObject;

public class TplClass5906 {

    private static final void method(java.lang.String SIGALG, java.security.KeyPair kp, java.security.SignedObject so2) throws Throwable {
        if (!so2.verify(kp.getPublic(), Signature.getInstance(SIGALG))) {
        }
    }
}

