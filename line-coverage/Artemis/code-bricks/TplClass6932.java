import java.util.zip.ZipInputStream;

public class TplClass6932 {

    private static final void method() throws Throwable {
        ZipInputStream z = new ZipInputStream(System.in);
        try {
            z.skip(-1);
        } catch (IllegalArgumentException e) {
        }
    }
}

