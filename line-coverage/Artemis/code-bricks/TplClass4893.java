public class TplClass4893 {

    private static final void method(double k, double d, double jPowerOf2, double absj, int MAX_RANGE) throws Throwable {
        for (int j = -MAX_RANGE; j <= MAX_RANGE; j++) {
            absj = (double) j;
            jPowerOf2 = absj * absj;
            d = k * jPowerOf2;
            if (j < 0)
                d = -d;
        }
    }
}

