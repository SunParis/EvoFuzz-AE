import java.lang.reflect.Method;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;

public class TplClass5719 {

    private static final void method() throws Throwable {
        // Base level
        Class c = Class.forName("java.lang.annotation.Retention");
        Annotation result = c.getAnnotation(Retention.class);
        // Meta level, invoke Class.getAnnotation reflectively...
        Class meta_c = c.getClass();
        Method meta_getAnnotation = meta_c.getMethod("getAnnotation", (Retention.class).getClass());
        Object meta_result = meta_getAnnotation.invoke(c, Retention.class);
        if (!meta_result.equals(result)) {
        }
        meta_getAnnotation.getGenericExceptionTypes();
        meta_getAnnotation.getGenericParameterTypes();
        meta_getAnnotation.getGenericReturnType();
    }
}

