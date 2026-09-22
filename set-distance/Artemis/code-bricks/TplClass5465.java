import java.lang.annotation.IncompleteAnnotationException;

public class TplClass5465 {

    private static final void method(int errors) throws Throwable {
        try {
            Object o = new IncompleteAnnotationException(null, null);
            errors++;
        } catch (NullPointerException npe) {
            // Expected
            ;
        }
    }
}

