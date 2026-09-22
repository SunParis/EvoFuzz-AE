import java.util.zip.ZipOutputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;

public class TplClass6292 {

    private static final void method(boolean appendGarbage, byte[] buf) throws Throwable {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ZipOutputStream zos = new ZipOutputStream(baos)) {
            final byte[] xbuf = { 'x' };
            zos.putNextEntry(new ZipEntry("a.gz"));
            GZIPOutputStream gos1 = new GZIPOutputStream(zos);
            gos1.write(xbuf);
            gos1.finish();
            if (appendGarbage)
                zos.write(xbuf);
            zos.closeEntry();
            zos.putNextEntry(new ZipEntry("b.gz"));
            GZIPOutputStream gos2 = new GZIPOutputStream(zos);
            gos2.write(xbuf);
            gos2.finish();
            zos.closeEntry();
            zos.flush();
            buf = baos.toByteArray();
        }
    }
}

