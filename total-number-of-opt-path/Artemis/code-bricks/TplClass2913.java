public class TplClass2913 {

    private static final void method(java.lang.Object[] memory, int allocationIndex) throws Throwable {
        memory[allocationIndex] = new Object[1024 / 4];
        ++allocationIndex;
        if (allocationIndex == memory.length) {
            allocationIndex = 0;
        }
    }
}

