public class TplClass5882 {

    private static final void method(int i, int errors) throws Throwable {
        short datum = (short) i;
        int ui = Short.toUnsignedInt(datum);
        if ((ui & (~0xffff)) != 0 || ((short) ui != datum)) {
            errors++;
        }
    }
}

