public class TplClass4725 {

    private static final void method(int[] mArrI) throws Throwable {
        mArrI[20] = 111;
        for (int i = 0; i < mArrI.length; i++) {
            mArrI[i] = mArrI[20] + 2;
        }
    }
}

