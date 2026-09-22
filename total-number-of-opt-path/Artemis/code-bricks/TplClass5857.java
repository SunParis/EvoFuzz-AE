public class TplClass5857 {

    private static final void method(int datum, int errors) throws Throwable {
        if (Integer.parseUnsignedInt(Integer.toOctalString(datum), 8) != datum) {
            errors++;
        }
    }
}

