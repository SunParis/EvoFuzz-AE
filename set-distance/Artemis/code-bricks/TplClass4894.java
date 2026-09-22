import java.text.NumberFormat;

public class TplClass4894 {

    private static final void method(java.lang.String str, double d, int fixedFractionalPart, int MAX_RANGE, java.text.NumberFormat nf, double fractionaScaling) throws Throwable {
        for (int j = -MAX_RANGE; j <= MAX_RANGE; j++) {
            d = (((double) j * fractionaScaling) + (double) fixedFractionalPart) / fractionaScaling;
            str = nf.format(d);
        }
    }
}

