import java.io.ByteArrayInputStream;
import java.util.jar.JarInputStream;

public class TplClass6948 {

    private static final void method() throws Throwable {
        try {
            JarInputStream is = new JarInputStream(new ByteArrayInputStream(new byte[0]));
        } catch (NullPointerException e) {
        }
    }
}

