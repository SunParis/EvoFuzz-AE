public class TplClass4918 {

    private static final void method(long currMemoryLeft, long oneMegabyte, int nbGCCalls, long refMemory) throws Throwable {
        nbGCCalls++;
        refMemory = currMemoryLeft;
        System.gc();
        currMemoryLeft = Runtime.getRuntime().freeMemory();
    }
}

