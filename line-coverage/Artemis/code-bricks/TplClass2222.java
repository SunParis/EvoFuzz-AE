import java.lang.reflect.Method;

public class TplClass2222 {

    private static final void method(java.lang.reflect.Method startMethodTracingMethod, java.lang.reflect.Method isDebuggerConnectedMethod, java.lang.reflect.Method resetAllocCountMethod, java.lang.reflect.Method isDebuggingEnabledMethod, java.lang.reflect.Method startMethodTracingDdmsMethod, java.lang.reflect.Method getAllocCountMethod, java.lang.reflect.Method getLoadedClassCountMethod, java.lang.reflect.Method stopAllocCountingMethod, java.lang.reflect.Method threadCpuTimeNanosMethod, java.lang.reflect.Method getRuntimeStatMethod, java.lang.reflect.Method dumpReferenceTablesMethod, java.lang.reflect.Method startAllocCountingMethod, java.lang.reflect.Method stopMethodTracingMethod, java.lang.reflect.Method lastDebuggerActivityMethod, java.lang.reflect.Method countInstancesOfClassMethod, java.lang.reflect.Method getMethodTracingModeMethod, java.lang.reflect.Method dumpHprofDataDdmsMethod, java.lang.reflect.Method getVmFeatureListMethod, java.lang.reflect.Method countInstancesOfClassesMethod, java.lang.reflect.Method setAllocTrackerStackDepthMethod, java.lang.reflect.Method getRuntimeStatsMethod) throws Throwable {
        try {
            Class<?> c = Class.forName("dalvik.system.VMDebug");
            startMethodTracingMethod = c.getDeclaredMethod("startMethodTracing", String.class, Integer.TYPE, Integer.TYPE, Boolean.TYPE, Integer.TYPE);
            startMethodTracingDdmsMethod = c.getDeclaredMethod("startMethodTracingDdms", Integer.TYPE, Integer.TYPE, Boolean.TYPE, Integer.TYPE);
            stopMethodTracingMethod = c.getDeclaredMethod("stopMethodTracing");
            getMethodTracingModeMethod = c.getDeclaredMethod("getMethodTracingMode");
            getRuntimeStatMethod = c.getDeclaredMethod("getRuntimeStat", String.class);
            getRuntimeStatsMethod = c.getDeclaredMethod("getRuntimeStats");
            countInstancesOfClassMethod = c.getDeclaredMethod("countInstancesOfClass", Class.class, Boolean.TYPE);
            countInstancesOfClassesMethod = c.getDeclaredMethod("countInstancesOfClasses", Class[].class, Boolean.TYPE);
            getAllocCountMethod = c.getDeclaredMethod("getAllocCount", Integer.TYPE);
            startAllocCountingMethod = c.getDeclaredMethod("startAllocCounting");
            stopAllocCountingMethod = c.getDeclaredMethod("stopAllocCounting");
            setAllocTrackerStackDepthMethod = c.getDeclaredMethod("setAllocTrackerStackDepth", Integer.TYPE);
            resetAllocCountMethod = c.getDeclaredMethod("resetAllocCount", Integer.TYPE);
            getLoadedClassCountMethod = c.getDeclaredMethod("getLoadedClassCount");
            getVmFeatureListMethod = c.getDeclaredMethod("getVmFeatureList");
            isDebuggerConnectedMethod = c.getDeclaredMethod("isDebuggerConnected");
            isDebuggingEnabledMethod = c.getDeclaredMethod("isDebuggingEnabled");
            lastDebuggerActivityMethod = c.getDeclaredMethod("lastDebuggerActivity");
            threadCpuTimeNanosMethod = c.getDeclaredMethod("threadCpuTimeNanos");
            dumpHprofDataDdmsMethod = c.getDeclaredMethod("dumpHprofDataDdms");
            dumpReferenceTablesMethod = c.getDeclaredMethod("dumpReferenceTables");
        } catch (Exception e) {
        }
    }
}

