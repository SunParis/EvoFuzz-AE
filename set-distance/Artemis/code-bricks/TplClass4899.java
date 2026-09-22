public class TplClass4899 {

    private static final void method(long currMemoryLeft, long oneMegabyte, int nbGCCalls, long refMemory) throws Throwable {
        do {
            nbGCCalls++;
            refMemory = currMemoryLeft;
            System.gc();
            currMemoryLeft = Runtime.getRuntime().freeMemory();
        } while ((Math.abs(currMemoryLeft - refMemory) > oneMegabyte) && (nbGCCalls < 10));
    }
}

