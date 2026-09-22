public class TplClass3684 {

    private static final void method(double[] aD) throws Throwable {
        // assigning the wrong J/D type to one of these would introduce errors.
        for (int i = 0; i < aD.length; i++) {
            aD[i] = aD.length - i - 1;
        }
    }
}

