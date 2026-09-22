import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import java.io.ByteArrayInputStream;

public class TplClass6476 {

    private static final void method(java.util.zip.Inflater inf, java.io.ByteArrayInputStream bis, java.util.zip.InflaterInputStream infOS) throws Throwable {
        try {
            infOS = new InflaterInputStream(bis, inf, -1);
        } catch (IllegalArgumentException e) {
        }
    }
}

