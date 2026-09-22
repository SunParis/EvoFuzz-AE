public class TplClass3309 {

    private static final void method(int[] sArrI, float[] sArrF) throws Throwable {
        sArrI[20] = -2;
        for (int i = 0; i < sArrF.length; i++) {
            sArrF[i] = sArrI[20] - 2;
        }
    }
}

