public class TplClass5580 {

    private static final void method(long[] data, int errors) throws Throwable {
        for (long datum : data) {
            if (Long.parseUnsignedLong(Long.toBinaryString(datum), 2) != datum) {
                errors++;
            }
            if (Long.parseUnsignedLong(Long.toOctalString(datum), 8) != datum) {
                errors++;
            }
            if (Long.parseUnsignedLong(Long.toHexString(datum), 16) != datum) {
                errors++;
            }
        }
    }
}

