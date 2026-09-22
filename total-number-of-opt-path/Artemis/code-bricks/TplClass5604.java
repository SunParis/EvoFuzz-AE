public class TplClass5604 {

    private static final void method(long datum, int errors) throws Throwable {
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

