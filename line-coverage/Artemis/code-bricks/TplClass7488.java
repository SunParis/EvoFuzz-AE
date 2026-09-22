public class TplClass7488 {

    private static final void method(java.lang.String[] infinityStrings, java.lang.String[] invalidStrings, java.lang.String[] NaNStrings) throws Throwable {
        int i;
        float d;
        // Test valid NaN strings
        for (i = 0; i < NaNStrings.length; i++) {
            if (!Float.isNaN(d = Float.parseFloat(NaNStrings[i]))) {
            }
        }
        // Test valid Infinity strings
        for (i = 0; i < infinityStrings.length; i++) {
            if (!Float.isInfinite(d = Float.parseFloat(infinityStrings[i]))) {
            }
            // check sign of result
            boolean negative = (infinityStrings[i].charAt(0) == '-');
            if (d != (negative ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY))
                ;
        }
        // Test almost valid strings
        for (i = 0; i < invalidStrings.length; i++) {
            try {
                float result;
                d = Float.parseFloat(invalidStrings[i]);
            } catch (NumberFormatException e) {
                // expected
            }
        }
    }
}

