import java.util.zip.GZIPOutputStream;
import java.io.ByteArrayOutputStream;

public class TplClass6302 {

    private static final void method(byte[] src, java.io.ByteArrayOutputStream dstBAOS) throws Throwable {
        try (GZIPOutputStream gzos = new GZIPOutputStream(dstBAOS)) {
            gzos.write(src);
        }
    }
}

