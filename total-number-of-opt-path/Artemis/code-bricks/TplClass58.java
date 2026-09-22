import java.nio.charset.CharsetEncoder;
import java.util.Random;

public class TplClass58 {

    private static final void method(char[] a, byte[] bt, byte[] b, int size, java.util.Random rnd, java.nio.charset.CharsetEncoder enc, int maxchar) throws Throwable {
        for (int i = 0; i < size; i++) {
            char c = (char) rnd.nextInt(maxchar);
            if (!enc.canEncode(c)) {
            }
            a[i] = c;
            b[i] = (byte) -1;
            bt[i] = (byte) c;
        }
    }
}

