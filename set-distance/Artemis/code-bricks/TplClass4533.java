public class TplClass4533 {

    private static final void method(java.lang.ClassLoader loader) throws Throwable {
        Class<?> altClass;
        try {
            altClass = loader.loadClass("Inaccessible2");
        } catch (ClassNotFoundException cnfe) {
            Throwable cause = cnfe.getCause();
            if (cause instanceof IllegalAccessError) {
            } else {
            }
        }
    }
}

