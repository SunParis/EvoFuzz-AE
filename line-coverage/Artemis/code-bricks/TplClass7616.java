import java.nio.file.FileSystemException;

public class TplClass7616 {

    private static final void method(java.nio.file.FileSystemException exc, java.lang.String otherFile, java.lang.String thisFile) throws Throwable {
        if (!exc.getFile().equals(thisFile) || !exc.getOtherFile().equals(otherFile))
            ;
    }
}

