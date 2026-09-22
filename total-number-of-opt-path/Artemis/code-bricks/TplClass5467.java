import java.lang.annotation.IncompleteAnnotationException;
import java.lang.annotation.Annotation;

public class TplClass5467 {

    private static final void method() throws Throwable {
        int errors = 0;
        Class<? extends Annotation> annotationType = Annotation.class;
        String elementName = "name";
        try {
            Object o = new IncompleteAnnotationException(null, null);
            errors++;
        } catch (NullPointerException npe) {
            // Expected
            ;
        }
        try {
            Object o = new IncompleteAnnotationException(annotationType, null);
            errors++;
        } catch (NullPointerException npe) {
            // Expected
            ;
        }
        try {
            Object o = new IncompleteAnnotationException(null, elementName);
            errors++;
        } catch (NullPointerException npe) {
            // Expected
            ;
        }
        if (errors != 0)
            ;
    }
}

