import java.util.Arrays;

public class TplClass67 {

    private static final void method(boolean is_underflow, char[] a, byte[] b, byte[] bt, char[] at, int SIZE, boolean failed) throws Throwable {
        if (!is_underflow || !Arrays.equals(b, bt) || !Arrays.equals(a, at)) {
            failed = true;
        }
    }
}

