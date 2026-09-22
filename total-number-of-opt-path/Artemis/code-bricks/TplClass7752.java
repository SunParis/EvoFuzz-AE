import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Collections;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TplClass7752 {

    private static final void method() throws Throwable {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            List gumbies = Collections.nCopies(50, "gumby");
            out.writeObject(gumbies);
            out.flush();
            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            if (!gumbies.equals(in.readObject()))
                ;
        } catch (Exception e) {
        }
    }
}

