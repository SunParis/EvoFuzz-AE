public class TplClass4737 {

    private static final void method(float[] mArrF, int[] mArrI) throws Throwable {
        mArrI[20] = -2;
        for (int i = 0; i < mArrF.length; i++) {
            mArrF[i] = mArrI[20] - 2;
        }
    }
}

