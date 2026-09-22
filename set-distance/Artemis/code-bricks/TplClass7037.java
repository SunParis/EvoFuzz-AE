import java.math.BigDecimal;

public class TplClass7037 {

    private static final void method() throws Throwable {
        BigDecimal[][] testCases = { // BD value which larger than Long.MaxValue
        // BD value which larger than Long.MaxValue
        { new BigDecimal("1.00000"), new BigDecimal("1") }, // BD value which larger than Long.MaxValue
        { new BigDecimal("1.000"), new BigDecimal("1") }, // BD value which larger than Long.MaxValue
        { new BigDecimal("1"), new BigDecimal("1") }, // BD value which larger than Long.MaxValue
        { new BigDecimal("0.1234"), new BigDecimal("0.1234") }, // BD value which larger than Long.MaxValue
        { new BigDecimal("0.12340"), new BigDecimal("0.1234") }, // BD value which larger than Long.MaxValue
        { new BigDecimal("0.12340000000"), new BigDecimal("0.1234") }, // BD value which larger than Long.MaxValue
        { new BigDecimal("1234.5678"), new BigDecimal("1234.5678") }, // BD value which larger than Long.MaxValue
        { new BigDecimal("1234.56780"), new BigDecimal("1234.5678") }, // BD value which larger than Long.MaxValue
        { new BigDecimal("1234.567800000"), new BigDecimal("1234.5678") }, // BD value which larger than Long.MaxValue
        { new BigDecimal("0"), new BigDecimal("0") }, // BD value which larger than Long.MaxValue
        { new BigDecimal("0e2"), BigDecimal.ZERO }, // BD value which larger than Long.MaxValue
        { new BigDecimal("0e-2"), BigDecimal.ZERO }, // BD value which larger than Long.MaxValue
        { new BigDecimal("0e42"), BigDecimal.ZERO }, // BD value which larger than Long.MaxValue
        { new BigDecimal("+0e42"), BigDecimal.ZERO }, // BD value which larger than Long.MaxValue
        { new BigDecimal("-0e42"), BigDecimal.ZERO }, // BD value which larger than Long.MaxValue
        { new BigDecimal("0e-42"), BigDecimal.ZERO }, // BD value which larger than Long.MaxValue
        { new BigDecimal("+0e-42"), BigDecimal.ZERO }, // BD value which larger than Long.MaxValue
        { new BigDecimal("-0e-42"), BigDecimal.ZERO }, // BD value which larger than Long.MaxValue
        { new BigDecimal("0e-2"), BigDecimal.ZERO }, // BD value which larger than Long.MaxValue
        { new BigDecimal("0e100"), BigDecimal.ZERO }, // BD value which larger than Long.MaxValue
        { new BigDecimal("0e-100"), BigDecimal.ZERO }, // BD value which larger than Long.MaxValue
        { new BigDecimal("10"), new BigDecimal("1e1") }, // BD value which larger than Long.MaxValue
        { new BigDecimal("20"), new BigDecimal("2e1") }, // BD value which larger than Long.MaxValue
        { new BigDecimal("100"), new BigDecimal("1e2") }, // BD value which larger than Long.MaxValue
        { new BigDecimal("1000000000"), new BigDecimal("1e9") }, // BD value which larger than Long.MaxValue
        { new BigDecimal("100000000e1"), new BigDecimal("1e9") }, // BD value which larger than Long.MaxValue
        { new BigDecimal("10000000e2"), new BigDecimal("1e9") }, // BD value which larger than Long.MaxValue
        { new BigDecimal("1000000e3"), new BigDecimal("1e9") }, { new BigDecimal("100000e4"), new BigDecimal("1e9") }, { new BigDecimal("1.0000000000000000000000000000"), new BigDecimal("1") }, { new BigDecimal("-1.0000000000000000000000000000"), new BigDecimal("-1") }, { new BigDecimal("1.00000000000000000000000000001"), new BigDecimal("1.00000000000000000000000000001") }, { new BigDecimal("1000000000000000000000000000000e4"), new BigDecimal("1e34") } };
        for (int i = 0; i < testCases.length; i++) {
            if (!(testCases[i][0]).stripTrailingZeros().equals(testCases[i][1])) {
            }
            testCases[i][0] = testCases[i][0].negate();
            testCases[i][1] = testCases[i][1].negate();
            if (!(testCases[i][0]).stripTrailingZeros().equals(testCases[i][1])) {
            }
        }
    }
}

