import java.io.File;

public class TplClass7254 {

    private static final void method(java.lang.String TESTFILE) throws Throwable {
        File fileToCreate = new File(TESTFILE);
        if (!fileToCreate.exists())
            if (!fileToCreate.createNewFile())
                ;
    }
}

