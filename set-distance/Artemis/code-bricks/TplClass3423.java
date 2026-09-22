public class TplClass3423 {

    private static final void method(int result, int[] array) throws Throwable {
        for (int j = 0; j < 2; ++j) {
            result += array[j];
            // The following operations would lead to BCE wanting to add another
            // deoptimization, but it crashed assuming the input of a `HBoundsCheck`
            // must be a `HArrayLength`.
            result += array[0];
            result += array[1];
            result += array[2];
        }
    }
}

