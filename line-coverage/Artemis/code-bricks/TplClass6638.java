import java.nio.channels.Pipe;

public class TplClass6638 {

    private static final void method(int PIPES_COUNT, java.nio.channels.Pipe[] pipes) throws Throwable {
        for (int i = 0; i < PIPES_COUNT; i++) {
            pipes[i].sink().close();
            pipes[i].source().close();
        }
    }
}

