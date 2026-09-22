public class TplClass5579 {

    private static final void method(int errors) throws Throwable {
        for (int i = Short.MIN_VALUE; i <= Short.MAX_VALUE; i++) {
            short datum = (short) i;
            long ui = Short.toUnsignedLong(datum);
            if ((ui & (~0xffffL)) != 0L || ((short) ui != datum)) {
                errors++;
            }
        }
    }
}

