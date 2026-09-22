import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class TplClass6162 {

    private static final void method(java.util.zip.ZipOutputStream zos) throws Throwable {
        ZipEntry ze = new ZipEntry("test");
        zos.putNextEntry(ze);
        byte[] hello = "hello, world".getBytes("ASCII");
        zos.write(hello, 0, hello.length);
        zos.closeEntry();
        zos.finish();
    }
}

