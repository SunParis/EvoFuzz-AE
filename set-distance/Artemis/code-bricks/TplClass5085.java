import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TplClass5085 {

    private static final void method() throws Throwable {
        Object result;
        try (ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(outBuffer)) {
            out.writeObject(String.CASE_INSENSITIVE_ORDER);
            out.close();
            try (ByteArrayInputStream inBuffer = new ByteArrayInputStream(outBuffer.toByteArray());
                ObjectInputStream in = new ObjectInputStream(inBuffer)) {
                result = in.readObject();
            }
        }
        if (!String.CASE_INSENSITIVE_ORDER.equals(result)) {
        }
        if (!result.equals(String.CASE_INSENSITIVE_ORDER)) {
        }
        if (String.CASE_INSENSITIVE_ORDER != result) {
        }
    }
}

