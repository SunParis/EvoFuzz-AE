import java.text.NumberFormat;

public class TplClass4928 {

    private static final void method(java.lang.String str, double d, double absj, java.text.NumberFormat nf, int j, double k, double jPowerOf2) throws Throwable {
        absj = (double) j;
        jPowerOf2 = absj * absj;
        d = k * jPowerOf2;
        if (j < 0)
            d = -d;
        str = nf.format(d);
    }
}

