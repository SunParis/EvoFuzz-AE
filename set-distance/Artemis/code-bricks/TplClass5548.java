import java.util.UUID;

public class TplClass5548 {

    private static final void method() throws Throwable {
        UUID test = UUID.randomUUID();
        try {
            test.timestamp();
        } catch (UnsupportedOperationException uoe) {
            // Correct result
        }
        test = UUID.fromString("00000001-0000-1000-8a5a-be785f17dcda");
        if (test.timestamp() != 1)
            ;
        test = UUID.fromString("00000400-0000-1000-8a5a-be785f17dcda");
        if (test.timestamp() != 1024)
            ;
        test = UUID.fromString("FFFFFFFF-FFFF-1FFF-8a5a-be785f17dcda");
        if (test.timestamp() != Long.MAX_VALUE >> 3)
            ;
    }
}

