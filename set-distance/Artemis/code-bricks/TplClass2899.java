public class TplClass2899 {

    private static final void method(long lFld, int[] iArrFld, double dFld) throws Throwable {
        // enough to allow JIT to compile the method for OSR and trigger the bug on host.
        long l = -1000000L;
        int i19 = 46, i20 = 100, i21, i22 = 13, i25;
        try {
            do for (; i19 < 172; ++i19) lFld = (long) dFld; while (++l < 146);
            for (i21 = 8; ; ++i21) for (i25 = 1; i25 < 2; i25++) {
                i20 = i22 % 1650388388;
                i20 = iArrFld[i21];
                i22 = 60;
            }
        } catch (ArrayIndexOutOfBoundsException exc1) {
        } finally {
        }
    }
}

