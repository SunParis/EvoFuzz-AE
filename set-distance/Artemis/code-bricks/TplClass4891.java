public class TplClass4891 {

    private static final void method(int id, double d, double seed, double[] valuesArray, int MAX_RANGE) throws Throwable {
        for (int j = -MAX_RANGE; j <= MAX_RANGE; j++) {
            id = (j >= 0) ? j % 9 : -j % 9;
            if ((j & 1) == 0)
                d = valuesArray[id] + id * seed;
            else
                d = valuesArray[id] - id * seed;
        }
    }
}

