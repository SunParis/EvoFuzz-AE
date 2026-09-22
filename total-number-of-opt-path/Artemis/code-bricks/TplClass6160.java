import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.ByteArrayOutputStream;

public class TplClass6160 {

    private static final void method(java.io.ByteArrayOutputStream baos) throws Throwable {
        try (ZipOutputStream zos = new ZipOutputStream(baos)) {
            ZipEntry ze = new ZipEntry("test");
            zos.putNextEntry(ze);
            byte[] hello = "hello, world".getBytes("ASCII");
            zos.write(hello, 0, hello.length);
            zos.closeEntry();
            zos.finish();
        }
    }
}

