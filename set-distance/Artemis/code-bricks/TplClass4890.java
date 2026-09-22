import java.text.NumberFormat;

public class TplClass4890 {

    private static final void method(java.lang.String str, double d, double seed, double[] valuesArray, int MAX_RANGE, java.text.NumberFormat nf, int id) throws Throwable {
        for (int j = -MAX_RANGE; j <= MAX_RANGE; j++) {
            id = (j >= 0) ? j % 9 : -j % 9;
            if ((j & 1) == 0)
                d = valuesArray[id] + id * seed;
            else
                d = valuesArray[id] - id * seed;
            str = nf.format(d);
        }
    }
}

