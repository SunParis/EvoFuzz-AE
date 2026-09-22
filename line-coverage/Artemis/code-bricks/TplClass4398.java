public class TplClass4398 {

    private static final void method(java.lang.ClassNotFoundException cnfe) throws Throwable {
        boolean unexpected = false;
        if (!(cnfe.getCause() instanceof InternalError)) {
            unexpected = true;
        } else {
            String message = cnfe.getCause().getMessage();
            unexpected = !message.startsWith("Attempt to register dex file ") || !message.endsWith(" with multiple class loaders");
        }
        if (unexpected) {
        }
    }
}

