public class TplClass6341 {

    private static final void method(java.lang.Exception e, java.lang.Exception failure) throws Throwable {
        if (failure == null) {
            failure = e;
        } else {
            failure.addSuppressed(e);
        }
    }
}

