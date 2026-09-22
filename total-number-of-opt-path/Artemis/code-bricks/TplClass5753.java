import java.text.NumberFormat;
import java.math.RoundingMode;

public class TplClass5753 {

    private static final void method(java.lang.String result, double[] src, java.lang.String[] expected, java.text.NumberFormat nf, int i, java.math.RoundingMode rm) throws Throwable {
        result = nf.parse(nf.format(src[i])).toString();
        if (!result.equals(expected[i])) {
        }
    }
}

