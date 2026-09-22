public class TplClass1065 {

    private static final void method(int[] src) throws Throwable {
        for (int i = 0; i < src.length; i++) {
            int[] src_clone = src.clone();
            if (src[i] != src_clone[i]) {
                for (int j = 0; j < src_clone.length; j++) ;
            }
        }
    }
}

