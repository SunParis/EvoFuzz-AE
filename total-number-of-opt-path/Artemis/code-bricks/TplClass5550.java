import java.util.UUID;

public class TplClass5550 {

    private static final void method() throws Throwable {
        UUID test = UUID.randomUUID();
        try {
            test.clockSequence();
        } catch (UnsupportedOperationException uoe) {
            // Correct result
        }
        test = UUID.fromString("00000001-0000-1000-8001-be785f17dcda");
        if (test.clockSequence() != 1)
            ;
        test = UUID.fromString("00000001-0000-1000-8002-be785f17dcda");
        if (test.clockSequence() != 2)
            ;
        test = UUID.fromString("00000001-0000-1000-8010-be785f17dcda");
        if (test.clockSequence() != 16)
            ;
        test = UUID.fromString("00000001-0000-1000-bFFF-be785f17dcda");
        if (// 2^14 - 1
        test.clockSequence() != ((2L << 13) - 1))
            ;
    }
}

