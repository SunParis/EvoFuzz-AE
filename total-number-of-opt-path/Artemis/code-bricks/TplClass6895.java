import java.lang.annotation.AnnotationTypeMismatchException;

public class TplClass6895 {

    private static final void method(java.lang.String TYPE) throws Throwable {
        AnnotationTypeMismatchException ex = new AnnotationTypeMismatchException(null, TYPE);
        if (!TYPE.equals(ex.foundType()))
            ;
    }
}

