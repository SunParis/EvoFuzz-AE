import java.net.URLClassLoader;

public class TplClass1007 {

    private static final void method(java.net.URLClassLoader apploader, long[] DIVISORS) throws Throwable {
        // Iterate over all divisors.
        for (int i = 0; i < DIVISORS.length; i++) {
            System.setProperty("divisor", "" + DIVISORS[i]);
            ClassLoader loader = new URLClassLoader(apploader.getURLs(), apploader.getParent());
            Class c = loader.loadClass("Test6800154");
            Runnable r = (Runnable) c.newInstance();
            r.run();
        }
    }
}

