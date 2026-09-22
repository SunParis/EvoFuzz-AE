import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TplClass2673 {

    private static final void method(java.lang.reflect.Field field, int actual) throws Throwable {
        Method getOffset = Field.class.getMethod("getOffset");
        actual = (Integer) getOffset.invoke(field);
    }
}

