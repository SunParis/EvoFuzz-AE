import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.FileSystems;

public class TplClass5984 {

    private static final void method(java.lang.String path) throws Throwable {
        try {
            Files.delete(FileSystems.getDefault().getPath(path));
        } catch (IOException ex) {
            // that is OK
        }
    }
}

