import java.util.zip.GZIPOutputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;

public class TplClass6304 {

    private static final void method(java.io.ByteArrayOutputStream srcBAOS, java.util.Random rnd, java.io.ByteArrayOutputStream dstBAOS) throws Throwable {
        byte[] src = new byte[rnd.nextInt(8192) + 1];
        rnd.nextBytes(src);
        srcBAOS.write(src);
        try (GZIPOutputStream gzos = new GZIPOutputStream(dstBAOS)) {
            gzos.write(src);
        }
    }
}

