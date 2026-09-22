public class TplClass4733 {

    private static final void method(java.lang.Object anObject, java.lang.Object anotherObject, java.lang.Object[] mArrL) throws Throwable {
        mArrL[20] = anotherObject;
        for (int i = 0; i < mArrL.length; i++) {
            mArrL[i] = (mArrL[20] == anObject) ? anotherObject : anObject;
        }
    }
}

