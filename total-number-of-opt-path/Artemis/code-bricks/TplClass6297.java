import java.util.zip.ZipInputStream;
import java.util.zip.GZIPInputStream;
import java.io.IOException;

public class TplClass6297 {

    private static final void method(java.util.zip.ZipInputStream zis, boolean limitGISBuff) throws Throwable {
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

