import java.text.NumberFormat;

public class TplClass4924 {

    private static final void method(java.lang.String str, double d, java.text.NumberFormat nf, double fractional, int j, double fractionalOdd, double fractionalEven) throws Throwable {
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

