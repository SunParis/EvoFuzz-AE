public class TplClass6349 {

    private static final void method(java.lang.Long[] VALUES, int TEST_SIZE, java.lang.Integer[] KEYS) throws Throwable {
        for (int each = 0; each < TEST_SIZE; each++) {
            KEYS[each] = Integer.valueOf(each);
            VALUES[each] = Long.valueOf(each + TEST_SIZE);
        }
    }
}

