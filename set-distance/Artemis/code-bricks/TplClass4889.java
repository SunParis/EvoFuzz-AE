public class TplClass4889 {

    private static final void method(double fractional, double d, double fractionalOdd, int MAX_RANGE, double fractionalEven) throws Throwable {
        for (int j = -MAX_RANGE; j <= MAX_RANGE; j++) {
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
}

