public class TplClass5608 {

    private static final void method(int i, int errors) throws Throwable {
        short datum = (short) i;
        long ui = Short.toUnsignedLong(datum);
        if ((ui & (~0xffffL)) != 0L || ((short) ui != datum)) {
            errors++;
        }
    }
}

