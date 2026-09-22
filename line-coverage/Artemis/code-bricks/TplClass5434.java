public class TplClass5434 {

    private static final void method(java.lang.String[] args) throws Throwable {
        Class<?> k = new Object() {
        }.getClass().getEnclosingClass();
        try {
            k.getMethod("instanceMain", String[].class).invoke(k.newInstance(), (Object) args);
        } catch (Throwable e) {
        }
    }
}

