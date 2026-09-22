import java.util.Hashtable;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;

public class TplClass6239 {

    private static final void method(java.util.Hashtable<java.lang.Object, java.lang.Object> hashtable) throws Throwable {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(hashtable);
        oos.close();
    }
}

