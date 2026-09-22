public class TplClass3790 {

    private static final void method(java.lang.Object[] sArray) throws Throwable {
        Object[] array = new Object[2];
        sArray = array;
        Object obj = array[0];
        // Store the same value as the default value.
        array[1] = obj;
    }
}

