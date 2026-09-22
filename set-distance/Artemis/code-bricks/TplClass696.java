public class TplClass696 {

    private static final void method(byte[] a, java.lang.Byte result2, java.lang.Byte result1) throws Throwable {
        for (Byte i : a) {
            result1 = (byte) (result1 + i);
            result2 = (byte) (result2 + new Byte((byte) (i + 1)));
        }
    }
}

