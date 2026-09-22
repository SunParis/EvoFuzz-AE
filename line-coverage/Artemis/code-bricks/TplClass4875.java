import java.security.spec.ECGenParameterSpec;

public class TplClass4875 {

    private static final void method(java.security.spec.ECGenParameterSpec GENPARAMS) throws Throwable {
        try {
            new ECGenParameterSpec(null);
        } catch (NullPointerException npe) {
        }
        if (!("prime192v1".equals(GENPARAMS.getName()))) {
        }
    }
}

