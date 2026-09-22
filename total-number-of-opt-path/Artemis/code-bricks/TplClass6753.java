import java.io.DataInputStream;
import java.io.IOException;

public class TplClass6753 {

    private static final void method(boolean error, java.io.DataInputStream is) throws Throwable {
        try {
            is.available();
        } catch (IOException ex) {
            error = false;
        }
    }
}

