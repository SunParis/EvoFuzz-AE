public class TplClass6105 {

    private static final void method(int i, java.lang.String[] infinityStrings, double d) throws Throwable {
        if (!Double.isInfinite(d = Double.parseDouble(infinityStrings[i]))) {
        }
        boolean negative = (infinityStrings[i].charAt(0) == '-');
        if (d != (negative ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY))
            ;
    }
}

