public class TplClass3291 {

    private static final void method(byte[] sArrB) throws Throwable {
        sArrB[20] = 11;
        for (int i = 0; i < sArrB.length; i++) {
            sArrB[i] = (byte) (sArrB[20] + 2);
        }
    }
}

