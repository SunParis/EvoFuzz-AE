import java.util.UUID;

public class TplClass5557 {

    private static final void method() throws Throwable {
        UUID id = new UUID(33L, 63L);
        UUID id2 = new UUID(34L, 62L);
        UUID id3 = new UUID(34L, 63L);
        UUID id4 = new UUID(34L, 64L);
        UUID id5 = new UUID(35L, 63L);
        if ((id.compareTo(id2) >= 0) || (id2.compareTo(id3) >= 0) || (id3.compareTo(id4) >= 0) || (id4.compareTo(id5) >= 0))
            ;
        if ((id5.compareTo(id4) <= 0) || (id4.compareTo(id3) <= 0) || (id3.compareTo(id2) <= 0) || (id2.compareTo(id) <= 0))
            ;
        if (id.compareTo(id) != 0)
            ;
    }
}

