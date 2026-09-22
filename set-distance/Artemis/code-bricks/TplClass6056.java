import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.beans.PropertyChangeSupport;

public class TplClass6056 {

    private static final void method(java.beans.PropertyChangeSupport pcs) throws Throwable {
        try {
            // serialize into byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream output = new ObjectOutputStream(baos);
            output.writeObject(pcs);
            output.flush();
            // deserialize from byte array
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream input = new ObjectInputStream(bais);
            pcs = (PropertyChangeSupport) input.readObject();
        } catch (Exception exception) {
        }
    }
}

