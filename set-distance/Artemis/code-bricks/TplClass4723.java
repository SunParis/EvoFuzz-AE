public class TplClass4723 {

    private static final void method(short[] mArrS) throws Throwable {
        mArrS[20] = 111;
        for (int i = 0; i < mArrS.length; i++) {
            mArrS[i] = (short) (mArrS[20] + 2);
        }
    }
}

