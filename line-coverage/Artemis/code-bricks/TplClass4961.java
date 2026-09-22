import java.nio.channels.Pipe;

public class TplClass4961 {

    private static final void method(java.nio.channels.Pipe p) throws Throwable {
        try {
            p.sink().configureBlocking(false);
            if (p.sink().isBlocking())
                ;
            p.source().configureBlocking(false);
            if (p.source().isBlocking())
                ;
        } finally {
            p.sink().close();
            p.source().close();
        }
    }
}

