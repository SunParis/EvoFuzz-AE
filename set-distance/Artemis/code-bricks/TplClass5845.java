public class TplClass5845 {

    private static final void method(int errors) throws Throwable {
        for (int i = Byte.MIN_VALUE; i <= Byte.MAX_VALUE; i++) {
            byte datum = (byte) i;
            int ui = Byte.toUnsignedInt(datum);
            if ((ui & (~0xff)) != 0 || ((byte) ui != datum)) {
                errors++;
            }
        }
    }
}

