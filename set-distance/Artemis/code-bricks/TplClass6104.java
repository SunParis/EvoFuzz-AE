public class TplClass6104 {

    private static final void method(java.lang.String[] infinityStrings, java.lang.String[] invalidStrings, java.lang.String[] NaNStrings) throws Throwable {
        int i;
        double d;
        // Test valid NaN strings
        for (i = 0; i < NaNStrings.length; i++) {
            if (!Double.isNaN(d = Double.parseDouble(NaNStrings[i]))) {
            }
        }
        // Test valid Infinity strings
        for (i = 0; i < infinityStrings.length; i++) {
            if (!Double.isInfinite(d = Double.parseDouble(infinityStrings[i]))) {
            }
            // check sign of result
            boolean negative = (infinityStrings[i].charAt(0) == '-');
            if (d != (negative ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY))
                ;
        }
        // Test almost valid strings
        for (i = 0; i < invalidStrings.length; i++) {
            try {
                double result;
                d = Double.parseDouble(invalidStrings[i]);
            } catch (NumberFormatException e) {
                // expected
            }
        }
    }
}

