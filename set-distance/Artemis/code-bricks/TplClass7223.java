import java.util.Arrays;
import java.util.Base64;

public class TplClass7223 {

    private static final void method(java.lang.String pkcs7path, byte[] encoded) throws Throwable {
        // check if it matches the encoded value
        if (!Arrays.equals(encoded, Base64.getMimeDecoder().decode(pkcs7path.getBytes()))) {
        }
    }
}

