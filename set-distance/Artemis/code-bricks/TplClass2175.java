public class TplClass2175 {

    private static final void method(float a, float b, float otherFloatField, boolean test1) throws Throwable {
        if (test1) {
            // The phi for `a` will be found to be of type float.
            a = otherFloatField;
            // The phi for `b` will be found to be of type int (constants in DEX).
            b = 33.0f;
        }
    }
}

