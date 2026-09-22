public class TplClass3305 {

    private static final void method(java.lang.Object anObject, java.lang.Object anotherObject, java.lang.Object[] sArrL) throws Throwable {
        sArrL[20] = anotherObject;
        for (int i = 0; i < sArrL.length; i++) {
            sArrL[i] = (sArrL[20] == anObject) ? anotherObject : anObject;
        }
    }
}

