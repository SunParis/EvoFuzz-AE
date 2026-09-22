public class TplClass5828 {

    private static final void method(int TEST_HIGH, int i) throws Throwable {
        while (i <= TEST_HIGH) {
            // check that valueOf stores i
            if (Integer.valueOf(i).intValue() != i)
                ;
            // check that the same object is returned for integral values
            // in the range -128 to 127 (inclusive)
            if (i >= -128 && i <= 127) {
                if (Integer.valueOf(i) != Integer.valueOf(i))
                    ;
            }
            i++;
        }
    }
}

