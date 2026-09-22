import java.util.Arrays;

public class TplClass66 {

    private static final void method(int result, char[] a, byte[] b, byte[] bt, char[] at, int SIZE, boolean failed) throws Throwable {
        if (result != 0 || !Arrays.equals(b, bt) || !Arrays.equals(a, at)) {
            failed = true;
        }
    }
}

