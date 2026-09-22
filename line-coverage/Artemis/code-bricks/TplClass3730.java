public class TplClass3730 {

    private static final void method(int used3, int invar4, int used4, int invar2, int used1, int invar3, int used2, int invar1) throws Throwable {
        for (int i = 0; i < 10000; i++) {
            used1 += invar1 + invar2;
            used2 -= used1 + invar2 - invar3;
            used3 *= used2 + invar3 * invar4;
            used4 /= used3 + invar1 * invar2 - invar3 + invar4;
        }
    }
}

