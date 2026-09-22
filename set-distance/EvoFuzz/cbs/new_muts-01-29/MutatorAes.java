import javax.crypto.Cipher;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;
public class MutatorAes {
    
    public static void $mut1(javax.crypto.Cipher $lval1, String $lval2, String $expr1) {
        try {
            $lval2 = new String($lval1.doFinal($expr1.getBytes()));
        } catch (Exception $tmp1) {
            $lval2 = $tmp1.getClass().getSimpleName();
        }
    }

    public static void $mut2(javax.crypto.Cipher $lval1, String $lval2, String $expr1) {
        try {
            $lval2 = java.util.Base64.getEncoder().encodeToString($lval1.doFinal($lval2.getBytes()));
        } catch (Exception $tmp1) {
            $lval2 = $tmp1.getClass().getSimpleName();
        }
    }

    public static String $mut3(javax.crypto.Cipher $lval1, String $expr1) {
        return new String($lval1.update($expr1.getBytes()));
    }

}
