import java.security.Signature;

public class TplClass6959 {

    private static final void method(java.security.Signature signature, byte[] sigBytes) throws Throwable {
        try {
            signature.verify(sigBytes, Integer.MAX_VALUE, 1);
        } catch (IllegalArgumentException ex) {
            // Expected
        }
    }
}

