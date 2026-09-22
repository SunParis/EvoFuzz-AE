import java.math.BigDecimal;

public class TplClass7108 {

    private static final void method(int failures, java.lang.String[] testCase) throws Throwable {
        BigDecimal bd = new BigDecimal(testCase[0]);
        String result = bd.toEngineeringString();
        if (!result.equals(testCase[1]) || !bd.equals(new BigDecimal(result))) {
            failures++;
        }
    }
}

