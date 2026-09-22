public class TplClass4727 {

    private static final void method(long[] mArrJ) throws Throwable {
        mArrJ[20] = 111;
        for (int i = 0; i < mArrJ.length; i++) {
            mArrJ[i] = mArrJ[20] + 2;
        }
    }
}

