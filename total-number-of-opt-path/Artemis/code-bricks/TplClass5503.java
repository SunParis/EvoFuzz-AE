import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.util.UUID;
import java.io.ObjectInputStream;

public class TplClass5503 {

    private static final void method() throws Throwable {
        UUID a = UUID.randomUUID();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(a);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        UUID b = (UUID) ois.readObject();
        if (!a.equals(b))
            ;
        oos.close();
        ois.close();
    }
}

