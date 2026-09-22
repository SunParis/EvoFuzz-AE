import java.util.zip.InflaterInputStream;
import java.io.ByteArrayInputStream;

public class TplClass6474 {

    private static final void method(java.io.ByteArrayInputStream bis, java.util.zip.InflaterInputStream infOS) throws Throwable {
        try {
            infOS = new InflaterInputStream(bis, null);
        } catch (NullPointerException e) {
        }
    }
}

