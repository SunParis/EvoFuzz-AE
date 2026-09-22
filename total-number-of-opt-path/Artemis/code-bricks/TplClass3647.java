public class TplClass3647 {

    private static final void method(int counter5, int counter4, int counter3) throws Throwable {
        /* counter4 is not resolved */
        for (int i = 0; i < 32767; i++) {
            if (i >= 0) {
                counter3++;
            } else {
                counter4++;
            }
            counter5++;
        }
    }
}

