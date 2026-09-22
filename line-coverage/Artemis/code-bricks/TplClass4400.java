public class TplClass4400 {

    private static final void method(java.lang.ClassNotFoundException cnfe, boolean unexpected) throws Throwable {
        String message = cnfe.getCause().getMessage();
        unexpected = !message.startsWith("Attempt to register dex file ") || !message.endsWith(" with multiple class loaders");
    }
}

