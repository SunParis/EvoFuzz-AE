import java.nio.channels.Pipe;

public class TplClass4963 {

    private static final void method(java.nio.channels.Pipe p) throws Throwable {
        p.sink().configureBlocking(false);
        if (p.sink().isBlocking())
            ;
        p.source().configureBlocking(false);
        if (p.source().isBlocking())
            ;
    }
}

