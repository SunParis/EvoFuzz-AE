import java.nio.charset.CharsetEncoder;
import java.util.Random;

public class TplClass89 {

    private static final void method(char[] a, byte[] bt, byte[] b, int i, java.util.Random rnd, java.nio.charset.CharsetEncoder enc, int maxchar) throws Throwable {
        char c = (char) rnd.nextInt(maxchar);
        if (!enc.canEncode(c)) {
        }
        a[i] = c;
        b[i] = (byte) -1;
        bt[i] = (byte) c;
    }
}

