import java.util.zip.InflaterInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class TplClass5965 {

    private static final void method() throws Throwable {
        boolean expOccurred = false;
        byte[] data = { 90, 91, 92, 93, 94, 95, 96, 97 };
        InflaterInputStream in = new InflaterInputStream(new ByteArrayInputStream(data));
        in.mark(-5);
        in.mark(6);
        in.mark(Integer.MAX_VALUE);
        try {
            in.reset();
        } catch (IOException e) {
            // Correct result
        }
        try {
            in.reset();
        } catch (IOException e) {
            // Correct result
        }
    }
}

