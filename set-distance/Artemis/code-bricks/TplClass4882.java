import java.text.NumberFormat;

public class TplClass4882 {

    private static final void method(java.lang.String str, java.text.NumberFormat nf, double floatingN, int MAX_RANGE) throws Throwable {
        for (int j = -MAX_RANGE; j <= MAX_RANGE; j++) str = nf.format(floatingN * (double) j);
    }
}

