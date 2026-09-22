import java.math.BigDecimal;

public class TplClass7163 {

    private static final void method(java.math.BigDecimal[] bd2, int i, int failures, java.math.BigDecimal[][] expectedResults, java.math.BigDecimal[] bd1) throws Throwable {
        for (int j = 0; j < bd2.length; j++) {
            if (!bd1[i].multiply(bd2[j]).equals(expectedResults[i][j])) {
                failures++;
            }
        }
    }
}

