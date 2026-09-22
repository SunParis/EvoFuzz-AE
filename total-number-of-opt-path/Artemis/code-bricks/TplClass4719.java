public class TplClass4719 {

    private static final void method(byte[] mArrB) throws Throwable {
        mArrB[20] = 111;
        for (int i = 0; i < mArrB.length; i++) {
            mArrB[i] = (byte) (mArrB[20] + 2);
        }
    }
}

