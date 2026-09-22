public class TplClass1630 {

    private static final void method(long val, long p1, int olen, long p2, byte[] outArr, long p3, long p4, byte pling, long rem) throws Throwable {
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

