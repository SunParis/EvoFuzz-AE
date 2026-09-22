import java.math.BigDecimal;

public class TplClass7165 {

    private static final void method(java.math.BigDecimal[] bd2, int failures, int i, int j, java.math.BigDecimal[][] expectedResults, java.math.BigDecimal[] bd1) throws Throwable {
        if (!bd1[i].multiply(bd2[j]).equals(expectedResults[i][j])) {
            failures++;
        }
    }
}

