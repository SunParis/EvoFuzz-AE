import java.util.zip.ZipInputStream;
import java.util.zip.GZIPInputStream;
import java.io.IOException;
import java.io.ByteArrayInputStream;

public class TplClass6293 {

    private static final void method(boolean limitGISBuff, byte[] buf) throws Throwable {
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

