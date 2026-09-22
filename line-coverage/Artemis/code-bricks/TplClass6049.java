import java.security.NoSuchAlgorithmException;
import java.security.cert.LDAPCertStoreParameters;
import java.security.cert.CertStore;

public class TplClass6049 {

    private static final void method() throws Throwable {
        try {
            CertStore.getInstance("LDAP", new LDAPCertStoreParameters());
        } catch (NoSuchAlgorithmException x) {
        }
    }
}

