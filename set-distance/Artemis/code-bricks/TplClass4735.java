public class TplClass4735 {

    private static final void method(float[] mArrF, int[] mArrI) throws Throwable {
        mArrF[20] = -1;
        for (int i = 0; i < mArrI.length; i++) {
            mArrI[i] = (int) mArrF[20] - 2;
        }
    }
}

