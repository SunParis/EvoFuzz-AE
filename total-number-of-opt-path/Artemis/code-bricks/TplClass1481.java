public class TplClass1481 {

    private static final void method(int i, short[] p2, byte[] a0) throws Throwable {
        short l = p2[i];
        a0[i * 2 + 0] = (byte) (l & 0xFF);
        a0[i * 2 + 1] = (byte) (l >> 8);
    }
}

