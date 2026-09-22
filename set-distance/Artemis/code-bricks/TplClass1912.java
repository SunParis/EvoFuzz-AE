import java.util.Random;

public class TplClass1912 {

    private static final void method(double[] res, java.util.Random r, int nb) throws Throwable {
        for (int i = 0; i < nb; i++) {
            // 2045 rather than 2046 as a safety margin
            double ylogx = (1 + (r.nextDouble() * 2045)) - 1023;
            double x = Math.E;
            double logx = Math.log(x) / Math.log(2);
            double y = ylogx / logx;
            res[i] = y;
        }
    }
}

