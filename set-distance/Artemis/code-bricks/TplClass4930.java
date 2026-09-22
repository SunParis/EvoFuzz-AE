import java.text.NumberFormat;

public class TplClass4930 {

    private static final void method(java.lang.String str, double d, int fixedFractionalPart, java.text.NumberFormat nf, int j, double fractionaScaling) throws Throwable {
        d = (((double) j * fractionaScaling) + (double) fixedFractionalPart) / fractionaScaling;
        str = nf.format(d);
    }
}

