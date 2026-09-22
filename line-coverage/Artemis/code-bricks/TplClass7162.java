import java.math.BigDecimal;

public class TplClass7162 {

    private static final void method(java.math.BigDecimal[] bd2, int failures, java.math.BigDecimal[][] expectedResults, java.math.BigDecimal[] bd1) throws Throwable {
        for (int i = 0; i < bd1.length; i++) {
            for (int j = 0; j < bd2.length; j++) {
                if (!bd1[i].multiply(bd2[j]).equals(expectedResults[i][j])) {
                    failures++;
                }
            }
        }
    }
}

