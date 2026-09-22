import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TplClass5083 {

    private static final void method(java.lang.Object result) throws Throwable {
        try (ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(outBuffer)) {
            out.writeObject(String.CASE_INSENSITIVE_ORDER);
            out.close();
            try (ByteArrayInputStream inBuffer = new ByteArrayInputStream(outBuffer.toByteArray());
                ObjectInputStream in = new ObjectInputStream(inBuffer)) {
                result = in.readObject();
            }
        }
    }
}

