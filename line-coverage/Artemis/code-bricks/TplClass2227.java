import java.lang.reflect.Method;

public class TplClass2227 {

    private static final void method(java.lang.reflect.Method startMethodTracingMethod, java.lang.String filename, boolean samplingEnabled, int flags, int intervalUs, int bufferSize) throws Throwable {
        startMethodTracingMethod.invoke(null, filename, bufferSize, flags, samplingEnabled, intervalUs);
    }
}

