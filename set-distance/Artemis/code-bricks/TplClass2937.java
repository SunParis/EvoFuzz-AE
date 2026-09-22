public class TplClass2937 {

    private static final void method() throws Throwable {
        Object o = new Object();
        // Generate a hashcode and put it in the lock word.
        int hashOrig = o.hashCode();
        int hashInflated = 0;
        int hashSystemOrig = System.identityHashCode(o);
        int hashSystemInflated = 0;
        // Inflate the monitor to move the hash from the lock word to the Monitor.
        synchronized (o) {
            hashInflated = o.hashCode();
            hashSystemInflated = System.identityHashCode(o);
        }
        // Make sure that all the hashes agree.
        if (hashOrig != hashInflated || hashOrig != hashSystemOrig || hashSystemOrig != hashSystemInflated) {
        }
    }
}

