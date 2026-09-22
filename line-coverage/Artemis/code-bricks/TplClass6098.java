public class TplClass6098 {

    private static final void method(int i, java.lang.String[] infinityStrings, double d) throws Throwable {
        // Test valid Infinity strings
        for (i = 0; i < infinityStrings.length; i++) {
            if (!Double.isInfinite(d = Double.parseDouble(infinityStrings[i]))) {
            }
            // check sign of result
            boolean negative = (infinityStrings[i].charAt(0) == '-');
            if (d != (negative ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY))
                ;
        }
    }
}

