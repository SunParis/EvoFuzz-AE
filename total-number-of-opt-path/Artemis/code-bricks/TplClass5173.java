import java.util.Vector;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class TplClass5173 {

    private static final void method(java.util.Vector vector) throws Throwable {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(vector);
        oos.close();
    }
}

