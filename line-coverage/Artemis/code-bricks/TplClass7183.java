import java.net.URI;
import java.io.File;

public class TplClass7183 {

    private static final void method() throws Throwable {
        URI one = new URI("Relative%20with%20spaces");
        URI two = (new File("/tmp/dir with spaces/File with spaces")).toURI();
        URI three = two.resolve(one);
        if (!three.getSchemeSpecificPart().equals(three.getPath()))
            ;
    }
}

