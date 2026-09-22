import java.math.BigInteger;
import java.security.spec.ECFieldFp;

public class TplClass4872 {

    private static final void method(java.math.BigInteger ZERO, java.security.spec.ECFieldFp FP, java.math.BigInteger TEN) throws Throwable {
        try {
            new ECFieldFp(ZERO);
        } catch (IllegalArgumentException iae) {
        }
        try {
            new ECFieldFp(null);
        } catch (NullPointerException npe) {
        }
        if (TEN.equals(FP.getP()) == false) {
        }
        if (FP.getFieldSize() != TEN.bitLength()) {
        }
    }
}

