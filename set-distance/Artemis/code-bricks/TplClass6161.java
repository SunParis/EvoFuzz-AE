import java.io.File;
import java.io.FileOutputStream;

public class TplClass6161 {

    private static final void method(byte[] data, java.io.File f) throws Throwable {
        try (FileOutputStream fos = new FileOutputStream(f)) {
            fos.write(data, 0, data.length);
        }
    }
}

