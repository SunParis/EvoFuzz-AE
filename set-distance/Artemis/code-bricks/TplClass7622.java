import java.nio.file.FileSystemException;
import java.util.Objects;

public class TplClass7622 {

    private static final void method(java.lang.String reason, java.lang.String otherFile, java.lang.String thisFile) throws Throwable {
        FileSystemException exc = new FileSystemException(thisFile, otherFile, reason);
        if (!Objects.equals(thisFile, exc.getFile()))
            ;
        if (!Objects.equals(otherFile, exc.getOtherFile()))
            ;
        if (!Objects.equals(reason, exc.getReason()))
            ;
    }
}

