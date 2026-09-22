import java.io.FileInputStream;
import java.io.FileDescriptor;

public class TplClass5446 {

    private static final void method() throws Throwable {
        // close FileDescriptor.in
        (new FileInputStream(FileDescriptor.in)).close();
        // get the inherited channel
        if (System.inheritedChannel() != null) {
        }
    }
}

