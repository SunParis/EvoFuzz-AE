public class TplClass6502 {

    private static final void method(byte[] a) throws Throwable {
        byte[] a2 = new byte[a.length * 2];
        System.arraycopy(a, 0, a2, 0, a.length);
        a = a2;
    }
}

