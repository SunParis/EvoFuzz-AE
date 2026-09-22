public class TplClass3646 {

    private static final void method(int counter5, int counter2, int counter1) throws Throwable {
        /* counter1 is not resolved */
        for (int i = 0; i < 32767; i++) {
            if (i < 0) {
                counter1++;
            } else {
                counter2++;
            }
            counter5++;
        }
    }
}

