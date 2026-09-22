import java.nio.charset.CharsetEncoder;
import java.util.Random;

public class TplClass79 {

    private static final void method(char[] a, byte[] bt, byte[] b, char[] at, int i, java.util.Random rnd, java.nio.charset.CharsetEncoder enc, int maxchar) throws Throwable {
        char c = (char) rnd.nextInt(maxchar);
        if (!enc.canEncode(c)) {
        }
        a[i] = c;
        b[i] = (byte) c;
        at[i] = (char) -1;
        bt[i] = (byte) -1;
    }
}

