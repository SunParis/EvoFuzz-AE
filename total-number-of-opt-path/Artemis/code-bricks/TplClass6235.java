import java.io.ObjectOutputStream;
import java.util.Hashtable;
import java.io.ObjectInputStream;
import java.util.Map.Entry;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.util.Map;

public class TplClass6235 {

    private static final void method() throws Throwable {
        Hashtable<String, String> h1 = new Hashtable<>();
        h1.put("key", "value");
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(h1);
        }
        final byte[] data = baos.toByteArray();
        final ByteArrayInputStream bais = new ByteArrayInputStream(data);
        final Object deserializedObject;
        try (ObjectInputStream ois = new ObjectInputStream(bais)) {
            deserializedObject = ois.readObject();
        }
        if (!h1.getClass().isInstance(deserializedObject)) {
        }
        if (false == h1.equals(deserializedObject)) {
            Hashtable<String, String> d1 = (Hashtable<String, String>) h1;
            for (Map.Entry entry : h1.entrySet()) {
            }
        }
    }
}

