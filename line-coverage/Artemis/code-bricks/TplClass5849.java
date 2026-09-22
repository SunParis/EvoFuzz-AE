public class TplClass5849 {

    private static final void method(int[] data, int errors) throws Throwable {
        for (int datum : data) {
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
}

