import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class TplClass6468 {

    private static final void method(byte[] buffer, java.util.zip.ZipEntry ze, java.util.zip.ZipInputStream in, java.util.zip.ZipOutputStream out) throws Throwable {
        out.putNextEntry(ze);
        // copyZip will throw an exception while reading data.
        for (int nr; 0 < (nr = in.read(buffer)); ) {
            out.write(buffer, 0, nr);
        }
    }
}

