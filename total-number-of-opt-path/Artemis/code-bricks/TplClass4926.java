import java.text.NumberFormat;

public class TplClass4926 {

    private static final void method(java.lang.String str, double d, double seed, double[] valuesArray, java.text.NumberFormat nf, int j, int id) throws Throwable {
        id = (j >= 0) ? j % 9 : -j % 9;
        if ((j & 1) == 0)
            d = valuesArray[id] + id * seed;
        else
            d = valuesArray[id] - id * seed;
        str = nf.format(d);
    }
}

