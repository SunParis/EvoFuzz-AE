public class TplClass4909 {

    private static final void method(int j, int id, double d, double seed, double[] valuesArray) throws Throwable {
        if ((j & 1) == 0)
            d = valuesArray[id] + id * seed;
        else
            d = valuesArray[id] - id * seed;
    }
}

