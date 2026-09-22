import java.math.BigDecimal;

public class TplClass7071 {

    private static final void method(java.lang.String[][] testCases, int failures) throws Throwable {
        for (String[] testCase : testCases) {
            BigDecimal bd = new BigDecimal(testCase[0]);
            String result = bd.toEngineeringString();
            if (!result.equals(testCase[1]) || !bd.equals(new BigDecimal(result))) {
                failures++;
            }
        }
    }
}

