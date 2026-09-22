import java.nio.channels.Pipe;

public class TplClass4952 {

    private static final void method(java.nio.channels.Pipe testPipe) throws Throwable {
        testPipe.sink().close();
        testPipe.source().close();
    }
}

