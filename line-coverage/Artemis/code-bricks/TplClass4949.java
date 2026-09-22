import java.nio.channels.Pipe;

public class TplClass4949 {

    private static final void method(java.nio.channels.Pipe testPipe) throws Throwable {
        if (testPipe != null) {
            testPipe.sink().close();
            testPipe.source().close();
        }
    }
}

