public class TplClass5852 {

    private static final void method(long[] inRange, int errors) throws Throwable {
        for (long value : inRange) {
            for (int radix = Character.MIN_RADIX; radix <= Character.MAX_RADIX; radix++) {
                String longString = Long.toString(value, radix);
                int intResult = Integer.parseUnsignedInt(longString, radix);
                if (Integer.toUnsignedLong(intResult) != value) {
                    errors++;
                }
            }
        }
    }
}

