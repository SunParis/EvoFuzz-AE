public class TplClass4925 {

    private static final void method(double fractional, int j, double d, double fractionalOdd, double fractionalEven) throws Throwable {
        if ((j & 1) == 0)
            fractional = fractionalEven;
        else
            fractional = fractionalOdd;
        if (j >= 0)
            d = (double) j + fractional;
        else
            d = (double) j - fractional;
    }
}

