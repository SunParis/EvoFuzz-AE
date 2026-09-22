public class TplClass3248 {

    private static final void method(java.lang.Object sL, java.lang.Object anotherObject, java.lang.Object[] sArrL) throws Throwable {
        for (int i = 0; i < sArrL.length; i++) {
            sArrL[i] = sL;
            if (i == 10)
                sL = anotherObject;
        }
    }
}

