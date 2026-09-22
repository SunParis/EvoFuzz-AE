import java.net.URLClassLoader;

public class TplClass1011 {

    private static final void method(long[] DIVISORS) throws Throwable {
        Class cl = Class.forName("Test6800154");
        URLClassLoader apploader = (URLClassLoader) cl.getClassLoader();
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

