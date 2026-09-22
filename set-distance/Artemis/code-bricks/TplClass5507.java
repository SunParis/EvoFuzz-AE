import java.util.UUID;

public class TplClass5507 {

    private static final void method() throws Throwable {
        // If two UUIDs are equal they must have the same hashCode
        for (int i = 0; i < 100; i++) {
            UUID u1 = UUID.randomUUID();
            UUID u2 = UUID.fromString(u1.toString());
            if (u1.hashCode() != u2.hashCode())
                ;
        }
    }
}

