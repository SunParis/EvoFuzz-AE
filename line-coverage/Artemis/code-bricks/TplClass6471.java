import java.io.ByteArrayOutputStream;
import java.util.zip.DeflaterOutputStream;

public class TplClass6471 {

    private static final void method(java.util.zip.DeflaterOutputStream defOS, java.io.ByteArrayOutputStream bos) throws Throwable {
        try {
            defOS = new DeflaterOutputStream(bos, null);
        } catch (NullPointerException e) {
        }
    }
}

