import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

public class TplClass6473 {

    private static final void method(java.util.zip.DeflaterOutputStream defOS, java.util.zip.Deflater def, java.io.ByteArrayOutputStream bos) throws Throwable {
        try {
            defOS = new DeflaterOutputStream(bos, def, -1);
        } catch (IllegalArgumentException e) {
        }
    }
}

