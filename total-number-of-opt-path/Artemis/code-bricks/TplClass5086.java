import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TplClass5086 {

    private static final void method(java.lang.Object result, java.io.ByteArrayOutputStream outBuffer, java.io.ObjectOutputStream out) throws Throwable {
        out.writeObject(String.CASE_INSENSITIVE_ORDER);
        out.close();
        try (ByteArrayInputStream inBuffer = new ByteArrayInputStream(outBuffer.toByteArray());
            ObjectInputStream in = new ObjectInputStream(inBuffer)) {
            result = in.readObject();
        }
    }
}

