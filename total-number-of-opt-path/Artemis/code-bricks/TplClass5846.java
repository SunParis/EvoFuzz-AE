public class TplClass5846 {

    private static final void method(int errors) throws Throwable {
        for (int i = Short.MIN_VALUE; i <= Short.MAX_VALUE; i++) {
            short datum = (short) i;
            int ui = Short.toUnsignedInt(datum);
            if ((ui & (~0xffff)) != 0 || ((short) ui != datum)) {
                errors++;
            }
        }
    }
}

