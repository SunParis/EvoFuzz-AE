public class TplClass5872 {

    private static final void method(long longRemainder, int quotient, long divisor, long dividend, int remainder, int errors, long longQuotient) throws Throwable {
        if (divisor == 0) {
            try {
                quotient = Integer.divideUnsigned((int) dividend, (int) divisor);
                errors++;
            } catch (ArithmeticException ea) {
                // Expected
                ;
            }
            try {
                remainder = Integer.remainderUnsigned((int) dividend, (int) divisor);
                errors++;
            } catch (ArithmeticException ea) {
                // Expected
                ;
            }
        } else {
            quotient = Integer.divideUnsigned((int) dividend, (int) divisor);
            longQuotient = dividend / divisor;
            if (quotient != (int) longQuotient) {
                errors++;
            }
            remainder = Integer.remainderUnsigned((int) dividend, (int) divisor);
            longRemainder = dividend % divisor;
            if (remainder != (int) longRemainder) {
                errors++;
            }
        }
    }
}

