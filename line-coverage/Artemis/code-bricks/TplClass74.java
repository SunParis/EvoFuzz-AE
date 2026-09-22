import java.util.Arrays;

public class TplClass74 {

    private static final void method(boolean enc_res, byte[] b, byte[] bt, boolean failed, int size) throws Throwable {
        if (!enc_res || !Arrays.equals(b, bt)) {
            failed = true;
        }
    }
}

