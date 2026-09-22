public class TplClass4668 {

    private static final void method(boolean[] mArrZ, boolean mZ) throws Throwable {
        for (int i = 0; i < mArrZ.length; i++) {
            mArrZ[i] = mZ;
            if (i == 10)
                mZ = !mZ;
        }
    }
}

