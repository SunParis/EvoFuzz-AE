public class TplClass5865 {

    private static final void method(int datum, long result, int errors) throws Throwable {
        // High-order bits should be zero
        if ((result & 0xffff_ffff_0000_0000L) != 0L) {
            errors++;
        }
    }
}

