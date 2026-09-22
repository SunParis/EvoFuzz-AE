import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECFieldF2m;
import java.security.spec.EllipticCurve;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPoint;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPublicKeySpec;

public class TplClass4879 {

    private static final void method(java.security.spec.ECFieldF2m F2M, java.security.spec.ECPublicKeySpec PUB_KEY, java.security.spec.EllipticCurve CURVE, java.security.spec.ECPrivateKeySpec PRIV_KEY, java.security.spec.ECGenParameterSpec GENPARAMS, java.security.spec.ECFieldFp FP, java.security.spec.ECParameterSpec PARAMS, java.security.spec.ECPoint POINT) throws Throwable {
        // make sure no unexpected exception when hashCode() is called.
        FP.hashCode();
        F2M.hashCode();
        GENPARAMS.hashCode();
        PARAMS.hashCode();
        POINT.hashCode();
        PRIV_KEY.hashCode();
        PUB_KEY.hashCode();
        CURVE.hashCode();
    }
}

