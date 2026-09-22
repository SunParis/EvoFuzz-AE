public class TplClass1869 {

    private static final void method(int result, int indx, int[] b, int sum) throws Throwable {
        sum += indx;
        result = sum;
        sum ^= b[indx & 7];
        result = sum;
    }
}

