import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.PublicKey;

public class TplClass6960 {

    private static final void method() throws Throwable {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        keyPairGenerator.initialize(1024);
        KeyPair keys = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keys.getPublic();
        byte[] sigBytes = new byte[100];
        Signature signature = Signature.getInstance("SHA1withDSA");
        signature.initVerify(publicKey);
        try {
            signature.verify(sigBytes, Integer.MAX_VALUE, 1);
        } catch (IllegalArgumentException ex) {
            // Expected
        }
    }
}

