import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;

public class TplClass6242 {

    private static final void method(java.lang.Object newObj, java.lang.Object oldObj) throws Throwable {
        // Create a stream in which to serialize the object.
        ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        ObjectOutputStream p = new ObjectOutputStream(ostream);
        // Serialize the object into the stream
        p.writeObject(oldObj);
        // Create an input stream from which to deserialize the object
        byte[] byteArray = ostream.toByteArray();
        ByteArrayInputStream istream = new ByteArrayInputStream(byteArray);
        ObjectInputStream q = new ObjectInputStream(istream);
        // Deserialize the object
        newObj = q.readObject();
    }
}

