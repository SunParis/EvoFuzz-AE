public class TplClass4206 {

    private static final void method() throws Throwable {
        // ShortSizedArray). Just test that we don't fail those checks.
        final int len = 10;
        System.arraycopy(new float[len], 0, new float[len], 0, len);
        System.arraycopy(new double[len], 0, new double[len], 0, len);
    }
}

