import java.io.IOException;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;

public class TplClass6514 {

    private static final void method(java.util.zip.ZipOutputStream out) throws Throwable {
        out.close();
        try {
            out.putNextEntry(new ZipEntry(""));
        } catch (IOException e) {
        }
    }
}

