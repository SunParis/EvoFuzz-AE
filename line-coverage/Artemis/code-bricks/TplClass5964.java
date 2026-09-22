import java.util.zip.InflaterInputStream;
import java.io.IOException;

public class TplClass5964 {

    private static final void method(java.util.zip.InflaterInputStream in) throws Throwable {
        try {
            in.reset();
        } catch (IOException e) {
            // Correct result
        }
    }
}

