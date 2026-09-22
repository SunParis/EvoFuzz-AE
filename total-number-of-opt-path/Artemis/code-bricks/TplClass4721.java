public class TplClass4721 {

    private static final void method(char[] mArrC) throws Throwable {
        mArrC[20] = 111;
        for (int i = 0; i < mArrC.length; i++) {
            mArrC[i] = (char) (mArrC[20] + 2);
        }
    }
}

