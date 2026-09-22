public class TplClass5586 {

    private static final void method(long datum, int errors) throws Throwable {
        if (Long.parseUnsignedLong(Long.toOctalString(datum), 8) != datum) {
            errors++;
        }
    }
}

