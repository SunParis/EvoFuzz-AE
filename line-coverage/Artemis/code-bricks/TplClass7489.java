public class TplClass7489 {

    private static final void method(int i, java.lang.String[] infinityStrings, float d) throws Throwable {
        if (!Float.isInfinite(d = Float.parseFloat(infinityStrings[i]))) {
        }
        boolean negative = (infinityStrings[i].charAt(0) == '-');
        if (d != (negative ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY))
            ;
    }
}

