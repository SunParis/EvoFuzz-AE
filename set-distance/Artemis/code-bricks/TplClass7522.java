import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.util.Set;

public class TplClass7522 {

    private static final void method(java.util.Set result, java.util.Set m) throws Throwable {
        try {
            // Serialize
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(m);
            out.flush();
            // Deserialize
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            out.close();
            ObjectInputStream in = new ObjectInputStream(bis);
            result = (Set) in.readObject();
            in.close();
        } catch (Exception e) {
        }
    }
}

