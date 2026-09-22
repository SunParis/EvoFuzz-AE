public class TplClass5606 {

    private static final void method(int i, int errors) throws Throwable {
        byte datum = (byte) i;
        long ui = Byte.toUnsignedLong(datum);
        if ((ui & (~0xffL)) != 0L || ((byte) ui != datum)) {
            errors++;
        }
    }
}

