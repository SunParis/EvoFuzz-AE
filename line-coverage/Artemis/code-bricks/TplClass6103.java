public class TplClass6103 {

    private static final void method(int i, java.lang.String[] invalidStrings, double d) throws Throwable {
        try {
            double result;
            d = Double.parseDouble(invalidStrings[i]);
        } catch (NumberFormatException e) {
            // expected
        }
    }
}

