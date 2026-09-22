public class TplClass4468 {

    private static final void method() throws Throwable {
        long veryLarge = 4096;
        Object[] holder = new Object[(int) veryLarge / 16];
        int count = 0;
        try {
            while (true) {
                // A bit over one page.
                holder[count++] = new Object[1025];
            }
        } catch (Throwable e) {
        }
        for (int i = 0; i < count; ++i) {
            holder[i] = null;
        }
        // pages are correctly coalesced together by the allocator.
        holder[0] = new Object[(int) veryLarge / 8];
    }
}

