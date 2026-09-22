import java.nio.file.DirectoryIteratorException;

public class TplClass7621 {

    private static final void method() throws Throwable {
        // NullPointerException
        try {
            new DirectoryIteratorException(null);
        } catch (NullPointerException expected) {
        }
    }
}

