import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.GZIPInputStream;
import java.io.IOException;
import java.io.ByteArrayInputStream;

public class TplClass6295 {

    private static final void method(boolean appendGarbage, boolean limitGISBuff) throws Throwable {
        byte[] buf;
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
        try (ByteArrayInputStream bais = new ByteArrayInputStream(buf);
            ZipInputStream zis = new ZipInputStream(bais)) {
            zis.getNextEntry();
            GZIPInputStream gis1 = limitGISBuff ? new GZIPInputStream(zis, 4) : new GZIPInputStream(zis);
            // try to read more than the entry has
            gis1.skip(2);
            try {
                zis.getNextEntry();
            } catch (IOException e) {
            }
        }
    }
}

