public class TplClass5868 {

    private static final void method(int datum, int radix, java.lang.String result2, int errors) throws Throwable {
        if (radix == 10) {
            String result3 = Integer.toUnsignedString(datum);
            if (!result2.equals(result3)) {
                errors++;
            }
        }
    }
}

