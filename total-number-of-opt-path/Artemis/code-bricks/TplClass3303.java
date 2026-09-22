public class TplClass3303 {

    private static final void method(double[] sArrD) throws Throwable {
        sArrD[20] = 11;
        for (int i = 0; i < sArrD.length; i++) {
            sArrD[i] = sArrD[20] + 2;
        }
    }
}

