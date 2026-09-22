import javax.crypto.Cipher;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;

public class MutatorAesEssential {
    
    public static Cipher $new1() {
        return null;
    }

    public static void $init1(Cipher $lval1) {
        try {
            $lval1 = Cipher.getInstance("AES/ECB/PKCS5Padding");
            $lval1.init(Cipher.DECRYPT_MODE, new SecretKeySpec("123456654321".getBytes("UTF-8"), "AES"));
        } catch (Exception $tmp1) {
        }
    }

}
