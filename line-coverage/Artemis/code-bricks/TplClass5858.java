public class TplClass5858 {

    private static final void method(int datum, int errors) throws Throwable {
        if (Integer.parseUnsignedInt(Integer.toHexString(datum), 16) != datum) {
            errors++;
        }
    }
}

