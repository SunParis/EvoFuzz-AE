public class TplClass5847 {

    private static final void method(int[] data, int errors) throws Throwable {
        for (int radix = Character.MIN_RADIX; radix <= Character.MAX_RADIX; radix++) {
            for (int datum : data) {
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
    }
}

