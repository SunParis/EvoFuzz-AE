import java.util.zip.ZipEntry;
import java.io.InputStream;
import java.io.IOException;

public class TplClass1774 {

    private static final void method(java.io.InputStream is, java.util.zip.ZipEntry e, byte[] bytes) throws Throwable {
        try {
            while (is.read(bytes) >= 0) {
            }
            is.close();
        } catch (IOException x) {
            x.printStackTrace();
        }
    }
}

