import java.util.Arrays;

public class TplClass420 {

    private static final void method() throws Throwable {
        for (int i = 0; i < 200000; i++) {
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
}

