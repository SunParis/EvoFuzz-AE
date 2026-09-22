public class TplClass4729 {

    private static final void method(float[] mArrF) throws Throwable {
        mArrF[20] = 111;
        for (int i = 0; i < mArrF.length; i++) {
            mArrF[i] = mArrF[20] + 2;
        }
    }
}

