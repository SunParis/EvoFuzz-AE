public class TplClass3307 {

    private static final void method(int[] sArrI, float[] sArrF) throws Throwable {
        sArrF[20] = -1;
        for (int i = 0; i < sArrI.length; i++) {
            sArrI[i] = (int) sArrF[20] - 2;
        }
    }
}

