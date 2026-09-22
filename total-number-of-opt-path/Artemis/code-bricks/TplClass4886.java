import java.text.NumberFormat;

public class TplClass4886 {

    private static final void method(java.lang.String str, java.text.NumberFormat nf, double d, double seed, int MAX_RANGE) throws Throwable {
        for (int j = -MAX_RANGE; j <= MAX_RANGE; j++) {
            d = d + 1.0d + seed;
            str = nf.format(d);
        }
    }
}

