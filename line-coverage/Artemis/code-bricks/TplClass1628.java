public class TplClass1628 {

    private static final void method(long val, long p1, int olen, long p2, byte[] outArr, long p3, long p4, byte[] inArr, int i, byte pling, long rem) throws Throwable {
        val = ((long) ((inArr[i++] & 0xff)) << 24) + ((long) ((inArr[i++] & 0xff)) << 16) + ((long) ((inArr[i++] & 0xff)) << 8) + ((long) (inArr[i++] & 0xff));
        if (val == 0) {
            outArr[olen++] = 'z';
        } else {
            rem = val;
            outArr[olen++] = (byte) (rem / p4 + pling);
            rem = rem % p4;
            outArr[olen++] = (byte) (rem / p3 + pling);
            rem = rem % p3;
            outArr[olen++] = (byte) (rem / p2 + pling);
            rem = rem % p2;
            outArr[olen++] = (byte) (rem / p1 + pling);
            rem = rem % p1;
            outArr[olen++] = (byte) (rem + pling);
        }
    }
}

