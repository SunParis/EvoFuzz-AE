import java.text.NumberFormat;

public class TplClass4884 {

    private static final void method(java.lang.String str, java.text.NumberFormat nf, int MAX_RANGE) throws Throwable {
        for (int j = -MAX_RANGE; j <= MAX_RANGE; j++) str = nf.format(((double) j) / 1000.0d);
    }
}

