public class TplClass5856 {

    private static final void method(int datum, int errors) throws Throwable {
        if (Integer.parseUnsignedInt(Integer.toBinaryString(datum), 2) != datum) {
            errors++;
        }
    }
}

