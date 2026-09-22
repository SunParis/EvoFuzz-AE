import java.security.KeyStore;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.cert.Certificate;

public class TplClass6332 {

    private static final void method() throws Throwable {
        // TEST null private key
        try {
            Certificate[] chain = new Certificate[0];
            KeyStore.PrivateKeyEntry pke = new KeyStore.PrivateKeyEntry(null, chain);
        } catch (NullPointerException npe) {
        }
    }
}

