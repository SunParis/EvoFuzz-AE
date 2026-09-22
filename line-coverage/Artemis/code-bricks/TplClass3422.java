public class TplClass3422 {

    private static final void method(int result, int start, int[] array) throws Throwable {
        for (int i = start; i < 3; i++) {
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
}

