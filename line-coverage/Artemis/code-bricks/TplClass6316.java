import java.util.zip.GZIPOutputStream;
import java.io.PipedOutputStream;

public class TplClass6316 {

    private static final void method(long count, java.lang.Throwable trouble, byte[] data, java.io.PipedOutputStream out) throws Throwable {
        try (GZIPOutputStream s = new GZIPOutputStream(out)) {
            for (long i = 0; i < count; i++) s.write(data, 0, data.length);
        } catch (Throwable t) {
            trouble = t;
        }
    }
}

