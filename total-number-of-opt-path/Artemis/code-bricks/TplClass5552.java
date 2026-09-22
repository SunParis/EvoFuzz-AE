import java.util.UUID;

public class TplClass5552 {

    private static final void method() throws Throwable {
        UUID test = UUID.randomUUID();
        try {
            test.node();
        } catch (UnsupportedOperationException uoe) {
            // Correct result
        }
        test = UUID.fromString("00000001-0000-1000-8001-000000000001");
        if (test.node() != 1)
            ;
        test = UUID.fromString("00000001-0000-1000-8002-FFFFFFFFFFFF");
        if (// 2^48 - 1
        test.node() != ((2L << 47) - 1))
            ;
    }
}

