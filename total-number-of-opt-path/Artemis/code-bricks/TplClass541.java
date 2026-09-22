import java.net.URLClassLoader;

public class TplClass541 {

    private static final void method(java.net.URLClassLoader apploader, int k) throws Throwable {
        long divisor = (1L << k) - 1;
        System.setProperty("divisor", "" + divisor);
        ClassLoader loader = new URLClassLoader(apploader.getURLs(), apploader.getParent());
        Class c = loader.loadClass("Test6805724");
        Runnable r = (Runnable) c.newInstance();
        r.run();
    }
}

