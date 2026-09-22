public class TplClass4412 {

    private static final void method(int sub, int sum, int[] data) throws Throwable {
        for (int i = 1; data[i] != 0; ++i) {
            sub -= sum;
            sum += data[i];
        }
    }
}

