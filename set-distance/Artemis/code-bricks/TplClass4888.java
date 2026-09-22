import java.text.NumberFormat;

public class TplClass4888 {

    private static final void method(java.lang.String str, double d, int MAX_RANGE, java.text.NumberFormat nf, double fractional, double fractionalOdd, double fractionalEven) throws Throwable {
        for (int j = -MAX_RANGE; j <= MAX_RANGE; j++) {
            if ((j & 1) == 0)
                fractional = fractionalEven;
            else
                fractional = fractionalOdd;
            if (j >= 0)
                d = (double) j + fractional;
            else
                d = (double) j - fractional;
            str = nf.format(d);
        }
    }
}

