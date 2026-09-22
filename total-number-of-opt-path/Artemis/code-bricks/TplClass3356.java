public class TplClass3356 {

    private static final void method(long a, long rem, long quot) throws Throwable {
        /*
             * Make the dividend positive shifting it right by 1 bit then get
             * the quotient an remainder and correct them properly
             */
        long aPos = a >>> 1;
        long bPos = 1000000000L >>> 1;
        quot = aPos / bPos;
        rem = aPos % bPos;
        // double the remainder and add 1 if 'a' is odd
        rem = (rem << 1) + (a & 1);
    }
}

