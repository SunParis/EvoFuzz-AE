public class TplClass3297 {

    private static final void method(int[] sArrI) throws Throwable {
        sArrI[20] = 11;
        for (int i = 0; i < sArrI.length; i++) {
            sArrI[i] = sArrI[20] + 2;
        }
    }
}

