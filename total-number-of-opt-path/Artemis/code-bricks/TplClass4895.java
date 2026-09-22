public class TplClass4895 {

    private static final void method(double d, int fixedFractionalPart, double fractionaScaling, int MAX_RANGE) throws Throwable {
        for (int j = -MAX_RANGE; j <= MAX_RANGE; j++) {
            d = (((double) j * fractionaScaling) + (double) fixedFractionalPart) / fractionaScaling;
        }
    }
}

