public class TplClass3834 {

    private static final void method(int mCount, int num) throws Throwable {
        int count = mCount;
        /* burn CPU; adjust end value so we exceed scheduler quantum */
        for (int j = 0; j < 5000; j++) {
            ;
        }
        count++;
        mCount = count;
    }
}

