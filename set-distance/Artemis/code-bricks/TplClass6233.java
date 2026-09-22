import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.util.Hashtable;

public class TplClass6233 {

    private static final void method(java.util.Hashtable<java.lang.String, java.lang.String> h1, java.io.ByteArrayOutputStream baos) throws Throwable {
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(h1);
        }
    }
}

