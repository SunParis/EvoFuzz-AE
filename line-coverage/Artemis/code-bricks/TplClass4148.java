public class TplClass4148 {

    private static final void method(java.lang.Object[] array) throws Throwable {
        for (int i = 0; i < array.length; i += 2) {
            array[i] = new String("Creating some garbage" + i);
        }
    }
}

