public class TplClass5887 {

    private static final void method(int datum, int errors) throws Throwable {
        long result = Integer.toUnsignedLong(datum);
        // High-order bits should be zero
        if ((result & 0xffff_ffff_0000_0000L) != 0L) {
            errors++;
        }
        // Lower-order bits should be equal to datum.
        int lowOrder = (int) (result & 0x0000_0000_ffff_ffffL);
        if (lowOrder != datum) {
            errors++;
        }
    }
}

