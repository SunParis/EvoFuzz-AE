public class TplClass4927 {

    private static final void method(int j, int id, double d, double seed, double[] valuesArray) throws Throwable {
        id = (j >= 0) ? j % 9 : -j % 9;
        if ((j & 1) == 0)
            d = valuesArray[id] + id * seed;
        else
            d = valuesArray[id] - id * seed;
    }
}

