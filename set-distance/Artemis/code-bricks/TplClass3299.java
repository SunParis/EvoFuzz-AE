public class TplClass3299 {

    private static final void method(long[] sArrJ) throws Throwable {
        sArrJ[20] = 11;
        for (int i = 0; i < sArrJ.length; i++) {
            sArrJ[i] = sArrJ[20] + 2;
        }
    }
}

