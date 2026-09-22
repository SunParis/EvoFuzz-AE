import java.lang.annotation.IncompleteAnnotationException;

public class TplClass5466 {

    private static final void method(int errors, java.lang.String elementName) throws Throwable {
        try {
            Object o = new IncompleteAnnotationException(null, elementName);
            errors++;
        } catch (NullPointerException npe) {
            // Expected
            ;
        }
    }
}

