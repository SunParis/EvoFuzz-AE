import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

public class TplClass5084 {

    private static final void method(java.lang.Object result, java.io.ByteArrayOutputStream outBuffer) throws Throwable {
        try (ByteArrayInputStream inBuffer = new ByteArrayInputStream(outBuffer.toByteArray());
            ObjectInputStream in = new ObjectInputStream(inBuffer)) {
            result = in.readObject();
        }
    }
}

