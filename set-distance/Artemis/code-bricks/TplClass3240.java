public class TplClass3240 {

    private static final void method(boolean sZ, boolean[] sArrZ) throws Throwable {
        for (int i = 0; i < sArrZ.length; i++) {
            sArrZ[i] = sZ;
            if (i == 10)
                sZ = !sZ;
        }
    }
}

