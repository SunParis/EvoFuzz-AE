import java.text.NumberFormat;

public class TplClass4892 {

    private static final void method(java.lang.String str, double d, double absj, int MAX_RANGE, java.text.NumberFormat nf, double k, double jPowerOf2) throws Throwable {
        for (int j = -MAX_RANGE; j <= MAX_RANGE; j++) {
            absj = (double) j;
            jPowerOf2 = absj * absj;
            d = k * jPowerOf2;
            if (j < 0)
                d = -d;
            str = nf.format(d);
        }
    }
}

