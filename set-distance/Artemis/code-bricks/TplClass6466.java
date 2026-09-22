import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class TplClass6466 {

    private static final void method(byte[] buffer, java.util.zip.ZipInputStream in, java.util.zip.ZipOutputStream out) throws Throwable {
        // copyZip will throw an exception while reading data.
        for (int nr; 0 < (nr = in.read(buffer)); ) {
            out.write(buffer, 0, nr);
        }
    }
}

