public class TplClass5878 {

    private static final void method(int datum, int errors) throws Throwable {
        if (Integer.parseUnsignedInt(Integer.toBinaryString(datum), 2) != datum) {
            errors++;
        }
        if (Integer.parseUnsignedInt(Integer.toOctalString(datum), 8) != datum) {
            errors++;
        }
        if (Integer.parseUnsignedInt(Integer.toHexString(datum), 16) != datum) {
            errors++;
        }
    }
}

