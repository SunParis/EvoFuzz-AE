import java.util.Arrays;

public class TplClass444 {

    private static final void method(int i) throws Throwable {
        byte[] a = new byte[16];
        int start = i & 7;
        int end = start + ((i >> 4) & 7);
        byte value = (byte) i;
        if ((i & 1) == 1)
            value = (byte) -value;
        Arrays.fill(a, start, end, value);
        boolean error = false;
        for (int j = start; j < end; j++) {
            if (a[j] != value) {
                error = true;
            }
        }
        if (error)
            ;
    }
}

