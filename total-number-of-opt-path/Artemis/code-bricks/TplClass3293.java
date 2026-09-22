public class TplClass3293 {

    private static final void method(char[] sArrC) throws Throwable {
        sArrC[20] = 11;
        for (int i = 0; i < sArrC.length; i++) {
            sArrC[i] = (char) (sArrC[20] + 2);
        }
    }
}

