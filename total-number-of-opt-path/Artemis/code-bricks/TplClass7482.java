public class TplClass7482 {

    private static final void method(int i, java.lang.String[] infinityStrings, float d) throws Throwable {
        // Test valid Infinity strings
        for (i = 0; i < infinityStrings.length; i++) {
            if (!Float.isInfinite(d = Float.parseFloat(infinityStrings[i]))) {
            }
            // check sign of result
            boolean negative = (infinityStrings[i].charAt(0) == '-');
            if (d != (negative ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY))
                ;
        }
    }
}

