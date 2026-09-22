public class TplClass5578 {

    private static final void method(int errors) throws Throwable {
        for (int i = Byte.MIN_VALUE; i <= Byte.MAX_VALUE; i++) {
            byte datum = (byte) i;
            long ui = Byte.toUnsignedLong(datum);
            if ((ui & (~0xffL)) != 0L || ((byte) ui != datum)) {
                errors++;
            }
        }
    }
}

