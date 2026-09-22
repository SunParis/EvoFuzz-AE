import java.net.URLClassLoader;

public class TplClass1012 {

    private static final void method(int i, java.net.URLClassLoader apploader, long[] DIVISORS) throws Throwable {
        System.setProperty("divisor", "" + DIVISORS[i]);
        ClassLoader loader = new URLClassLoader(apploader.getURLs(), apploader.getParent());
        Class c = loader.loadClass("Test6800154");
        Runnable r = (Runnable) c.newInstance();
        r.run();
    }
}

