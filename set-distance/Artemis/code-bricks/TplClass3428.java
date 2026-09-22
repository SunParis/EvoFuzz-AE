public class TplClass3428 {

    private static final void method(int result, int i, int[] array) throws Throwable {
        result += array[i];
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

