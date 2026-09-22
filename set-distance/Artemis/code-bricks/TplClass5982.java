import java.nio.file.Files;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;

public class TplClass5982 {

    private static final void method(java.lang.String path) throws Throwable {
        if (Files.notExists(FileSystems.getDefault().getPath(path)))
            ;
    }
}

