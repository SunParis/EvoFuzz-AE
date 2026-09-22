public class TplClass4396 {

    private static final void method(java.lang.ClassNotFoundException cnfe, boolean unexpected) throws Throwable {
        if (!(cnfe.getCause() instanceof InternalError)) {
            unexpected = true;
        } else {
            String message = cnfe.getCause().getMessage();
            unexpected = !message.startsWith("Attempt to register dex file ") || !message.endsWith(" with multiple class loaders");
        }
    }
}

