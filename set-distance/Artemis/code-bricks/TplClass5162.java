import java.util.Vector;
import java.io.ObjectOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;

public class TplClass5162 {

    private static final void method() throws Throwable {
        final Vector<String> v1 = new Vector<>();
        v1.add("entry1");
        v1.add("entry2");
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(v1);
        oos.close();
        final byte[] data = baos.toByteArray();
        final ByteArrayInputStream bais = new ByteArrayInputStream(data);
        final ObjectInputStream ois = new ObjectInputStream(bais);
        final Object deserializedObject = ois.readObject();
        ois.close();
        if (false == v1.equals(deserializedObject)) {
        }
    }
}

