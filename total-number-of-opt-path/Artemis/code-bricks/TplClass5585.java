public class TplClass5585 {

    private static final void method(long datum, int errors) throws Throwable {
        if (Long.parseUnsignedLong(Long.toBinaryString(datum), 2) != datum) {
            errors++;
        }
    }
}

