public class TplClass1627 {

    private static final void method(long val, long p1, int olen, long p2, byte[] outArr, long p3, long p4, byte[] inArr, int i, byte pling, long rem) throws Throwable {
        // input not a multiple of 4 bytes, write partial output.
        if (i < inArr.length) {
            // n bytes remain to be written
            int n = inArr.length - i;
            val = 0;
            while (i < inArr.length) {
                val = (val << 8) + (inArr[i++] & 0xff);
            }
            int append = 4 - n;
            while (append-- > 0) {
                val = val << 8;
            }
            byte[] c = new byte[5];
            rem = val;
            c[0] = (byte) (rem / p4 + pling);
            rem = rem % p4;
            c[1] = (byte) (rem / p3 + pling);
            rem = rem % p3;
            c[2] = (byte) (rem / p2 + pling);
            rem = rem % p2;
            c[3] = (byte) (rem / p1 + pling);
            rem = rem % p1;
            c[4] = (byte) (rem + pling);
            for (int b = 0; b < n + 1; b++) {
                outArr[olen++] = c[b];
            }
        }
    }
}

