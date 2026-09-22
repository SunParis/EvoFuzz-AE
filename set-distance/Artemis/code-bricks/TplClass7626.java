import java.security.KeyPairGenerator;
import java.security.KeyPair;

public class TplClass7626 {

    private static final void method() throws Throwable {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
        kpg.initialize(512);
        // test generateKeyPair
        KeyPair kpair = kpg.generateKeyPair();
        if (kpair == null) {
        }
        // test genKeyPair
        kpair = kpg.genKeyPair();
        if (kpair == null) {
        }
    }
}

