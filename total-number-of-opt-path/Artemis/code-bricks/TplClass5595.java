public class TplClass5595 {

    private static final void method(long datum, int radix, java.lang.String result2, int errors) throws Throwable {
        if (radix == 10) {
            String result3 = Long.toUnsignedString(datum);
            if (!result2.equals(result3)) {
                errors++;
            }
        }
    }
}

