import java.util.List;
import java.util.UUID;
import java.util.Random;

public class TplClass5544 {

    private static final void method(java.util.Random byteSource, java.util.List list, byte[] someBytes) throws Throwable {
        byteSource.nextBytes(someBytes);
        UUID u1 = UUID.nameUUIDFromBytes(someBytes);
        if (3 != u1.version()) {
        }
        if (2 != u1.variant()) {
        }
        if (list.contains(u1))
            ;
        list.add(u1);
    }
}

