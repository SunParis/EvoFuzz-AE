import java.io.RandomAccessFile;
import java.io.FileNotFoundException;
import java.io.File;

public class TplClass4517 {

    private static final void method(java.lang.String pathName, java.io.File path, java.io.RandomAccessFile raf) throws Throwable {
        try {
            raf = new RandomAccessFile(path, "r");
        } catch (FileNotFoundException fnfe) {
        }
    }
}

