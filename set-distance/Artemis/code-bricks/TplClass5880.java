public class TplClass5880 {

    private static final void method(int i, int errors) throws Throwable {
        byte datum = (byte) i;
        int ui = Byte.toUnsignedInt(datum);
        if ((ui & (~0xff)) != 0 || ((byte) ui != datum)) {
            errors++;
        }
    }
}

