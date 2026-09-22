import java.nio.channels.Pipe;

public class TplClass4962 {

    private static final void method() throws Throwable {
        Pipe p = Pipe.open();
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

