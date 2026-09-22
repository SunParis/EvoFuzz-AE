public class TplClass7487 {

    private static final void method(int i, java.lang.String[] invalidStrings, float d) throws Throwable {
        try {
            float result;
            d = Float.parseFloat(invalidStrings[i]);
        } catch (NumberFormatException e) {
            // expected
        }
    }
}

