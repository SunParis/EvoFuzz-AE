import java.nio.channels.Pipe;

public class TplClass6642 {

    private static final void method(int i, java.nio.channels.Pipe[] pipes) throws Throwable {
        pipes[i].sink().close();
        pipes[i].source().close();
    }
}

