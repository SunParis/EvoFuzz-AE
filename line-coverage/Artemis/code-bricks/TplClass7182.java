import java.net.URI;
import java.io.File;
import java.net.URISyntaxException;

public class TplClass7182 {

    private static final void method() throws Throwable {
        try {
            URI one = new URI("Relative%20with%20spaces");
            URI two = (new File("/tmp/dir with spaces/File with spaces")).toURI();
            URI three = two.resolve(one);
            if (!three.getSchemeSpecificPart().equals(three.getPath()))
                ;
        } catch (URISyntaxException e) {
        }
    }
}

