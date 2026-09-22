public class TplClass1877 {

    private static final void method(int ITERATIONS, int sum) throws Throwable {
        for (int it = 0; it < ITERATIONS; ++it) {
            short value = 0;
            do {
                sum += Integer.bitCount(value);
            } while (++value != 0);
        }
    }
}

