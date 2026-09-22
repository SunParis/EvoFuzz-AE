import java.math.BigDecimal;

public class TplClass7176 {

    private static final void method(java.math.BigDecimal bd2, int failures, java.math.BigDecimal[] expectedResult, java.math.BigDecimal[] bd1) throws Throwable {
        for (int i = 0; i < bd1.length; i++) {
            if (!bd1[i].add(bd2).equals(expectedResult[i]))
                failures++;
        }
    }
}

