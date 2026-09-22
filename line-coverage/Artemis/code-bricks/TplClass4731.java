public class TplClass4731 {

    private static final void method(double[] mArrD) throws Throwable {
        mArrD[20] = 111;
        for (int i = 0; i < mArrD.length; i++) {
            mArrD[i] = mArrD[20] + 2;
        }
    }
}

