public class TplClass5889 {

    private static final void method(int datum, int radix, int errors) throws Throwable {
        String result1 = Integer.toUnsignedString(datum, radix);
        String result2 = Long.toString(Integer.toUnsignedLong(datum), radix);
        if (!result1.equals(result2)) {
            errors++;
        }
        if (radix == 10) {
            String result3 = Integer.toUnsignedString(datum);
            if (!result2.equals(result3)) {
                errors++;
            }
        }
        int parseResult = Integer.parseUnsignedInt(result1, radix);
        if (parseResult != datum) {
            errors++;
        }
    }
}

