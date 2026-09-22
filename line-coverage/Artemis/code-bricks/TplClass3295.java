public class TplClass3295 {

    private static final void method(short[] sArrS) throws Throwable {
        sArrS[20] = 11;
        for (int i = 0; i < sArrS.length; i++) {
            sArrS[i] = (short) (sArrS[20] + 2);
        }
    }
}

