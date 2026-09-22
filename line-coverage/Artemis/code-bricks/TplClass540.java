import java.net.URLClassLoader;

public class TplClass540 {

    private static final void method() throws Throwable {
        Class cl = Class.forName("Test6805724");
        URLClassLoader apploader = (URLClassLoader) cl.getClassLoader();
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

