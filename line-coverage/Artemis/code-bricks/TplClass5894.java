public class TplClass5894 {

    private static final void method(int radix, long value, int errors) throws Throwable {
        String longString = Long.toString(value, radix);
        int intResult = Integer.parseUnsignedInt(longString, radix);
        if (Integer.toUnsignedLong(intResult) != value) {
            errors++;
        }
    }
}

