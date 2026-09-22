import java.net.URLClassLoader;

public class TplClass536 {

    private static final void method(java.net.URLClassLoader apploader) throws Throwable {
        // Iterate over all 2^k-1 divisors.
        for (int k = 1; k < Long.SIZE; k++) {
            long divisor = (1L << k) - 1;
            System.setProperty("divisor", "" + divisor);
            ClassLoader loader = new URLClassLoader(apploader.getURLs(), apploader.getParent());
            Class c = loader.loadClass("Test6805724");
            Runnable r = (Runnable) c.newInstance();
            r.run();
        }
    }
}

