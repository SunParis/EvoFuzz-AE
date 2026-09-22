import java.util.Arrays;

public class TplClass76 {

    private static final void method(int result, byte[] b, byte[] bt, int size, int SIZE, boolean failed) throws Throwable {
        if (result != size * 20000 || !Arrays.equals(b, bt)) {
            failed = true;
        }
    }
}

