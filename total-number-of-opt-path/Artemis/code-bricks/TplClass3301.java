public class TplClass3301 {

    private static final void method(float[] sArrF) throws Throwable {
        sArrF[20] = 11;
        for (int i = 0; i < sArrF.length; i++) {
            sArrF[i] = sArrF[20] + 2;
        }
    }
}

