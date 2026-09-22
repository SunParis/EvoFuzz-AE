public class TplClass5270 {

    private static final void method() throws Throwable {
        StackTraceElement ste = new StackTraceElement("com.acme.Widget", "frobnicate", "Widget.java", 42);
        if (!(ste.getClassName().equals("com.acme.Widget") && ste.getFileName().equals("Widget.java") && ste.getMethodName().equals("frobnicate") && ste.getLineNumber() == 42))
            ;
        if (ste.isNativeMethod())
            ;
        StackTraceElement ste2 = new StackTraceElement("com.acme.Widget", "frobnicate", "Widget.java", -2);
        if (!ste2.isNativeMethod())
            ;
    }
}

