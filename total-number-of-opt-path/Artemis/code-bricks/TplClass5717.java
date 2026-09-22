public class TplClass5717 {

    private static final void method() throws Throwable {
        Throwable cause = new Throwable("because");
        Throwable par = new Throwable(cause);
        TypeNotPresentException cnp = new TypeNotPresentException("test", par);
        try {
        } catch (TypeNotPresentException e) {
            if (par != e.getCause())
                ;
        }
    }
}

