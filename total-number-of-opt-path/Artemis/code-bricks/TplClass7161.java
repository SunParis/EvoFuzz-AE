import java.math.BigDecimal;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class TplClass7161 {

    private static final void method(java.math.BigDecimal bd) throws Throwable {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(bd);
        oos.flush();
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
        BigDecimal tmp = (BigDecimal) ois.readObject();
        if (!bd.equals(tmp) || bd.hashCode() != tmp.hashCode()) {
        }
    }
}

