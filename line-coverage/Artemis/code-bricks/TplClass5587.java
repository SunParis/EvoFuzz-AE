public class TplClass5587 {

    private static final void method(long datum, int errors) throws Throwable {
        if (Long.parseUnsignedLong(Long.toHexString(datum), 16) != datum) {
            errors++;
        }
    }
}

