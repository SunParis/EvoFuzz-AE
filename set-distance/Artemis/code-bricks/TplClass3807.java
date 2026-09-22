import java.util.ArrayList;
import java.lang.reflect.Field;

public class TplClass3807 {

    private static final void method(java.lang.Object[] dexElements, java.util.ArrayList<java.lang.Object> dexFiles, java.lang.reflect.Field f) throws Throwable {
        for (Object element : dexElements) {
            Object dexFile = f.get(element);
            // Make copy.
            Field fileNameField = dexFile.getClass().getDeclaredField("mFileName");
            fileNameField.setAccessible(true);
            dexFiles.add(dexFile.getClass().getDeclaredConstructor(String.class).newInstance(fileNameField.get(dexFile)));
        }
    }
}

