import java.math.BigDecimal;

public class TplClass7177 {

    private static final void method(java.math.BigDecimal bd2, int i, int failures, java.math.BigDecimal[] expectedResult, java.math.BigDecimal[] bd1) throws Throwable {
        if (!bd1[i].add(bd2).equals(expectedResult[i]))
            failures++;
    }
}

