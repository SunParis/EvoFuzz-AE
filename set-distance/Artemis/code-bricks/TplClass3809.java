import java.util.ArrayList;
import java.lang.reflect.Field;

public class TplClass3809 {

    private static final void method(java.util.ArrayList<java.lang.Object> dexFiles, java.lang.reflect.Field f, java.lang.Object element) throws Throwable {
        Object dexFile = f.get(element);
        // Make copy.
        Field fileNameField = dexFile.getClass().getDeclaredField("mFileName");
        fileNameField.setAccessible(true);
        dexFiles.add(dexFile.getClass().getDeclaredConstructor(String.class).newInstance(fileNameField.get(dexFile)));
    }
}

