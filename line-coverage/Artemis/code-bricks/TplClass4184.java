public class TplClass4184 {

    private static final void method(java.lang.Object[] sArray) throws Throwable {
        Object[] array = sArray;
        Object nonNull = array[0];
        // Ensure nonNull has an implicit null check.
        nonNull.getClass();
        array[1] = nonNull;
    }
}

