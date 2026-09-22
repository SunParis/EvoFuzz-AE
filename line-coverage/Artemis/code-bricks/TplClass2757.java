import java.lang.reflect.Method;

public class TplClass2757 {

    private static final void method(java.lang.reflect.Method startMethodTracingMethod, java.lang.reflect.Method getMethodTracingModeMethod, java.lang.reflect.Method stopMethodTracingMethod) throws Throwable {
        Class<?> c = Class.forName("dalvik.system.VMDebug");
        startMethodTracingMethod = c.getDeclaredMethod("startMethodTracing", String.class, Integer.TYPE, Integer.TYPE, Boolean.TYPE, Integer.TYPE);
        stopMethodTracingMethod = c.getDeclaredMethod("stopMethodTracing");
        getMethodTracingModeMethod = c.getDeclaredMethod("getMethodTracingMode");
    }
}

