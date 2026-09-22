public class TplClass4676 {

    private static final void method(java.lang.Object anotherObject, java.lang.Object[] mArrL, java.lang.Object mL) throws Throwable {
        for (int i = 0; i < mArrL.length; i++) {
            mArrL[i] = mL;
            if (i == 10)
                mL = anotherObject;
        }
    }
}

