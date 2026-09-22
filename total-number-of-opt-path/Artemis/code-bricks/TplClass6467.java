import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class TplClass6467 {

    private static final void method(java.util.zip.ZipInputStream in, java.util.zip.ZipOutputStream out) throws Throwable {
        byte[] buffer = new byte[1 << 14];
        for (ZipEntry ze; (ze = in.getNextEntry()) != null; ) {
            out.putNextEntry(ze);
            // When the bug is present, it shows up here.  The second call to
            // copyZip will throw an exception while reading data.
            for (int nr; 0 < (nr = in.read(buffer)); ) {
                out.write(buffer, 0, nr);
            }
        }
        in.close();
    }
}

