import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

public class TplClass6234 {

    private static final void method(java.lang.Object deserializedObject, java.io.ByteArrayInputStream bais) throws Throwable {
        try (ObjectInputStream ois = new ObjectInputStream(bais)) {
            deserializedObject = ois.readObject();
        }
    }
}

